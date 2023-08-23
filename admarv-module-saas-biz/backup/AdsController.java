package com.admarv.saas.ads.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.model.ads.Ads;
import com.admarv.saas.fb.model.ads.Data;
import com.admarv.saas.fb.model.ads.dto.resp.RespGetAds;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.utils.JacksonUtils;
import com.google.api.client.util.Lists;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;

/**
 * 
 * @author liuliu
 *
 */
//@RestController
public class AdsController {
	
	private static final Logger log = LoggerFactory.getLogger(AdsController.class);
	
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private FacebookClientService facebookClientService;
	
	//@RequestMapping(value = "/admarv/getAds", method = RequestMethod.GET)
	public String getAds(String userId) {
		log.info("/admarv/getAds userId:{}", userId);
		List<RespGetAds> listRespGetAds = Lists.newArrayList();
		
		SysUserFbBind selectEntity = new SysUserFbBind();
		selectEntity.setUserId(userId);
		SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
		String adAccountId = sysUserFbBind.getAdAccountId();
		FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
		JsonMapper jsonMapper = facebookClient.getJsonMapper();
		String fields = "id,name";
		JsonObject jsonObject = facebookClient.fetchObject(adAccountId + "/ads", JsonObject.class, 
				Parameter.with("fields", fields));
		String srtjson = jsonMapper.toJson(jsonObject);
		Ads ads = JacksonUtils.fromJson(srtjson, Ads.class);
		List<Data> dataList = ads.getData();
		
		for (Data data : dataList) {
			RespGetAds respGetAds = new RespGetAds();
			String id = data.getId();
			String name = data.getName();
			respGetAds.setId(id);
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
