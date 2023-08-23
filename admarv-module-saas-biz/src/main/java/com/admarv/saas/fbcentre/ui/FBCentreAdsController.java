package com.admarv.saas.fbcentre.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.model.ads.dto.resp.RespGetAds;
import com.admarv.saas.mapper.AdInfoMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.AdInfo;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.utils.JacksonUtils;
import com.google.api.client.util.Lists;

/**
 * 
 * @author liuliu
 *
 */
@RestController
public class FBCentreAdsController {

	private static final Logger log = LoggerFactory.getLogger(FBCentreAdsController.class);
		
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private AdInfoMapper adInfoMapper;
    	
	@RequestMapping(value = "/admarv/getAds", method = RequestMethod.GET)
	public String getAds(String userId) {
		log.info("/admarv/getAds userId:{}", userId);
		SysUserFbBind selectSysUserFbBind = new SysUserFbBind();
		selectSysUserFbBind.setUserId(userId);
		SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectSysUserFbBind);
		String adAccountId = sysUserFbBind.getAdAccountId();
		
		List<RespGetAds> listRespGetAds = Lists.newArrayList();
		
		AdInfo selectEntity = new AdInfo();
		selectEntity.setAdAccountId(adAccountId);
		selectEntity.setStatus("ACTIVE");
		List<AdInfo> adInfoList = adInfoMapper.selectByEntity(selectEntity);
		for (AdInfo adsInfo : adInfoList) {
			RespGetAds respGetAds = new RespGetAds();
			String adId = adsInfo.getAdId();
			String name = adsInfo.getName();
			respGetAds.setId(adId);
			respGetAds.setName(name);
			listRespGetAds.add(respGetAds);
		}
		
		Response response = new Response();
		response.setCode("200");
		response.setResult(listRespGetAds);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}
}
