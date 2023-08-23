package com.admarv.saas.fb.lead.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admarv.saas.fb.lead.constant.FlwpStatEnum;
import com.admarv.saas.fb.lead.constant.LeadStatEnum;
import com.admarv.saas.mapper.LeadGenMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.mapper.SysUserMapper;
import com.admarv.saas.mapper.SysUserRoleMapper;
import com.admarv.saas.model.LeadCntry;
import com.admarv.saas.model.LeadGen;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.model.SysUserRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 询盘单服务
 * 
 * @author liuliu
 *
 */
@Service
public class LeadGenService {

    private static final Logger log = LoggerFactory.getLogger(LeadGenService.class);
    
    private static final String MGR_ROLE_ID = "1260924539346472964";
    
    @Autowired
    private LeadGenMapper leadGenMapper;
    
    @Autowired 
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    
    /**
     * 获取询盘详情
     * 
     * @param user
     * @return
     */
    public LeadGen getLeadDetail(int leadId) {
        LeadGen leadGen = leadGenMapper.selectByPrimaryKey(leadId);
        log.info("leadGen :{}", leadGen);
        return leadGen;
    }
    
    /**
     * 获取sass平台账户绑定的FB询盘数据
     * 
     * @param user
     * 
     */
    public List<LeadGen> getLeadGenByUser(String userName) {
        LeadGen selectUserName = new LeadGen();
        selectUserName.setUserName(userName);
        List<LeadGen> listLeadGenUserName = leadGenMapper.selectByEntity(selectUserName);
        log.info("list LeadGen userName:{}", listLeadGenUserName);
        LeadGen selectOwnerName = new LeadGen();
        selectOwnerName.setOwnerName(userName);
        List<LeadGen> listLeadGenOwnerName = leadGenMapper.selectByEntity(selectOwnerName);
        log.info("list LeadGen OwnerName:{}", listLeadGenOwnerName);
        List<LeadGen> mergedList = new ArrayList<>(listLeadGenUserName);
        mergedList.addAll(listLeadGenOwnerName);
        log.info("merged List:{}", mergedList);
        return mergedList;
    }
    
    /**
     * 根据跟单员查询
     * 
     * @param user
     * 
     */
    public PageInfo<LeadGen> getDispLeads(LeadQueryDO leadQueryDO) {
        log.info("getDispLeads leadQueryDO:{}", leadQueryDO);
        String userId = leadQueryDO.getUserId();
        int pageNum = leadQueryDO.getPageNum();
        int pageSize = leadQueryDO.getPageSize();
        String leadAuality = leadQueryDO.getLeadAuality();
        String flwpStat = leadQueryDO.getFlwpStat();
        String leadStat = leadQueryDO.getLeadStat();
        String rsrc = leadQueryDO.getRsrc();
        String cntryCd = leadQueryDO.getCntryCd();
        String strtDt = leadQueryDO.getStartDate();
        String endDt = leadQueryDO.getEndDate();
        String ownerId = leadQueryDO.getOwnerId();
        String name = leadQueryDO.getName();
        
        SysUserRole selSysUserRole = new SysUserRole();
        selSysUserRole.setUserId(userId);
        SysUserRole sysUserRole = sysUserRoleMapper.selectOneByEntity(selSysUserRole);
        String role = sysUserRole.getRoleId();
        if (!MGR_ROLE_ID.equals(role)) {
            SysUserFbBind selSysUserFbBind = new SysUserFbBind();
            selSysUserFbBind.setUserId(userId);
            SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selSysUserFbBind);
            String pageId = sysUserFbBind.getPageId();
            LeadGen selectEntity = new LeadGen();
            selectEntity.setPageId(pageId);
            selectEntity.setUserId(userId);
            selectEntity.setOwnerId(ownerId);
            selectEntity.setFlwpStat(flwpStat);
            selectEntity.setLeadAuality(leadAuality);
            selectEntity.setName(name);
            selectEntity.setLeadStat(leadStat);
            selectEntity.setRsrc(rsrc);
            selectEntity.setRegn(cntryCd);
            selectEntity.setStartDate(strtDt);
            selectEntity.setEndDate(endDt);
            PageHelper.startPage(pageNum, pageSize);
            PageHelper.orderBy("crte_tm DESC");
            List<LeadGen> dispLeads = leadGenMapper.selectDispLeads(selectEntity);
            log.info("display Leads:{}", dispLeads);
            PageInfo<LeadGen> pageinfo = new PageInfo<LeadGen>(dispLeads);
            log.info("pageinfo Leads:{}", dispLeads);
            return pageinfo;
        } else { 
            //如果是管理员且ownerId不为空则表示从筛选框来的请求
            if (StringUtils.isNotBlank(ownerId)) {
                LeadGen selectEntity = new LeadGen();
                selectEntity.setOwnerId(ownerId);
                selectEntity.setLeadAuality(leadAuality);
                selectEntity.setFlwpStat(flwpStat);
                selectEntity.setLeadStat(leadStat);
                selectEntity.setRsrc(rsrc);
                selectEntity.setRegn(cntryCd);
                selectEntity.setStartDate(strtDt);
                selectEntity.setEndDate(endDt);
                PageHelper.startPage(pageNum, pageSize);
                PageHelper.orderBy("crte_tm DESC");
                List<LeadGen> dispLeads = leadGenMapper.selectByEntity(selectEntity);
                log.info("display Leads:{}", dispLeads);
                PageInfo<LeadGen> pageinfo = new PageInfo<LeadGen>(dispLeads);
                log.info("pageinfo Leads:{}", pageinfo);
                return pageinfo;
            } else {
                LeadGen selectEntity = new LeadGen();
                selectEntity.setUserId(userId);
    	        selectEntity.setName(name);
                selectEntity.setOwnerId(ownerId);
                selectEntity.setFlwpStat(flwpStat);
                selectEntity.setLeadAuality(leadAuality);
                selectEntity.setLeadStat(leadStat);
                selectEntity.setRsrc(rsrc);
                selectEntity.setRegn(cntryCd);
                selectEntity.setStartDate(strtDt);
                selectEntity.setEndDate(endDt);
                PageHelper.startPage(pageNum, pageSize);
                PageHelper.orderBy("crte_tm DESC");
                List<LeadGen> dispLeads = leadGenMapper.selectByEntity(selectEntity);
                log.info("display Leads:{}", dispLeads);
                PageInfo<LeadGen> pageinfo = new PageInfo<LeadGen>(dispLeads);
                log.info("pageinfo Leads:{}", pageinfo);
                return pageinfo;
            }
        }
    }
    
    /**
     * 根据跟单员查询
     * 
     * @param user
     * 
     */
    public List<LeadGen> getLeadGenByOwner(String owner) {
        LeadGen selectUserName = new LeadGen();
        List<LeadGen> listLeadGenUserName = leadGenMapper.selectByEntity(selectUserName);
        log.info("list LeadGen userName:{}", listLeadGenUserName);
        LeadGen selectOwnerName = new LeadGen();
        List<LeadGen> listLeadGenOwnerName = leadGenMapper.selectByEntity(selectOwnerName);
        log.info("list LeadGen OwnerName:{}", listLeadGenOwnerName);
        List<LeadGen> mergedList = new ArrayList<>(listLeadGenUserName);
        mergedList.addAll(listLeadGenOwnerName);
        log.info("merged List:{}", mergedList);
        return mergedList;
    }
    
    /**
     * 根据跟单员查询
     * 
     * @param user
     * 
     */
    public List<LeadGen> getLeadGenByOwnerId(String ownerId) {
        LeadGen selectEntity = new LeadGen();
        selectEntity.setOwnerId(ownerId);
        List<LeadGen> leadGenList = leadGenMapper.selectByEntity(selectEntity);
        log.info("list LeadGen :{}", leadGenList);
        return leadGenList;
    }
    
    /**
     * 持久化广告线索询盘
     * 
     * @param leadGen
     * 
     */
    public void insertLeadGen(LeadGen leadGen) {
        log.info("insert LeadGen :{}", leadGen);
        String leadId = leadGen.getLeadId();
        LeadGen selectEntity = new LeadGen();
        selectEntity.setLeadId(leadId);
        LeadGen entity = leadGenMapper.selectOneByEntity(selectEntity);
        if (entity == null) {
            int row = leadGenMapper.insert(leadGen);
            log.info("success insert row :{}", row);
        }
    }
    
    /**
     * 编辑询盘单
     */
    public int updateLeadGen(int id, String leadStat, String flwpStat, String leadAuality) {
        LeadGen updateLeadGen = leadGenMapper.selectByPrimaryKey(id);
        if(StringUtils.isNotBlank(leadStat)) {
            updateLeadGen.setLeadStat(LeadStatEnum.getByCode(leadStat).getCode());
        }
        if(StringUtils.isNotBlank(flwpStat)) {
            updateLeadGen.setFlwpStat(FlwpStatEnum.getByCode(flwpStat).getCode());
        }
        if(StringUtils.isNotBlank(leadAuality)) {
            updateLeadGen.setLeadAuality(leadAuality);
        }
        int row = leadGenMapper.updateByPrimaryKey(updateLeadGen);
        log.info("success update row :{}", row);
        return row;
    }
    
    /**
     * 分配询盘单给跟单员
     * 
     * TODO: owner 和 userName 改为 ownerId、ownerName
     */
    public int leadgenAllocate(int leadId, String userId) {
        SysUser selectEnity = new SysUser();
        selectEnity.setUserId(userId);
        SysUser sysUser = sysUserMapper.selectOneByEntity(selectEnity);
        String userName = sysUser.getUserName();
        LeadGen updateLeadGen = leadGenMapper.selectByPrimaryKey(leadId);
        updateLeadGen.setOwnerId(userId);
        updateLeadGen.setOwnerName(userName);
        int row = leadGenMapper.updateByPrimaryKey(updateLeadGen);
        log.info("success update row :{}", row);
        return row;
    }
    
    /**
     * 获取当日询盘数量
     */
    public List<LeadGen> getLeadCountToday(String userName) {
        LeadGen selectUserName = new LeadGen();
        selectUserName.setUserName(userName);
        List<LeadGen> listLeadGenUserName = leadGenMapper.selectByEntity(selectUserName);
        log.info("list LeadGen userName:{}", listLeadGenUserName);
        LeadGen selectOwnerName = new LeadGen();
        selectOwnerName.setOwnerName(userName);
        List<LeadGen> listLeadGenOwnerName = leadGenMapper.selectByEntity(selectOwnerName);
        log.info("list LeadGen OwnerName:{}", listLeadGenOwnerName);
        List<LeadGen> mergedList = new ArrayList<>(listLeadGenUserName);
        mergedList.addAll(listLeadGenOwnerName);
        log.info("merged List:{}", mergedList);
        return mergedList;
    }
    
    /**
     * 按照leadId获取询盘
     */
    public List<LeadGen> getLeadByIds(List<Integer> ids) {
        List<LeadGen> listLeadGenUserName = leadGenMapper.selectByIdList(ids);
        log.info("list LeadGen UserName:{}", listLeadGenUserName);
        return listLeadGenUserName;
    }
    
    /**
     * 获取前N询盘数量的国家
     */
    public List<LeadCntry> countInquiriesByRegn(int top) {
        List<LeadCntry> listLeadCntry = leadGenMapper.countInquiriesByRegn(top);
        log.info("listLeadCntry:{}", listLeadCntry);
        return listLeadCntry;
    }
    
    /**
     * 获取所有询盘
     */
	public List<LeadGen> getAllLeads(String date, String userId) {
		LeadGen selectEntity = new LeadGen();
		selectEntity.setCrteDt(date);
		selectEntity.setUserId(userId);
		List<LeadGen> listLeadGen = leadGenMapper.selectByEntity(selectEntity);
		log.info("listLeadGen:{}", listLeadGen);
		return listLeadGen;
	}
    
}
