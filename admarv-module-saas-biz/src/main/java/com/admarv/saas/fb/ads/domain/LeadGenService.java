package com.admarv.saas.fb.ads.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admarv.saas.fb.ads.constant.FlwpStatEnum;
import com.admarv.saas.fb.ads.constant.LeadStatEnum;
import com.admarv.saas.mapper.LeadGenMapper;
import com.admarv.saas.model.LeadGen;

/**
 * 询盘单服务
 * 
 * @author liuliu
 *
 */
@Service
public class LeadGenService {

    private static final Logger log = LoggerFactory.getLogger(LeadGenService.class);
    
    @Autowired
    private LeadGenMapper leadGenMapper;
    
    /**
     * 获取本用户相关的询盘
     * 
     * @param user
     * @return
     */
    public List<LeadGen> getLeadGenByUser(String user) {
        LeadGen selectEntity = new LeadGen();
        selectEntity.setUserName(user);
        List<LeadGen> listLeadGen = leadGenMapper.selectByEntity(selectEntity);
        log.info("listLeadGen :{}", listLeadGen);
        return listLeadGen;
    }
    
    /**
     * 持久化广告线索询盘
     * 
     * @param leadGen
     */
    public void insertLeadGen(LeadGen leadGen) {
        String leadId = leadGen.getLeadId();
        LeadGen selectEntity = new LeadGen();
        selectEntity.setLeadId(leadId);
        LeadGen entity = leadGenMapper.selectByOneEntity(selectEntity);
        if (entity == null) {
            int row = leadGenMapper.insert(leadGen);
            log.info("success insert row :{}", row);
        }
    }
    
    /**
     * 编辑询盘单
     */
    public int updateLeadGen(int id, String leadStat, String flwpStat) {
        LeadGen updateLeadGen = leadGenMapper.selectByPrimaryKey(id);
        updateLeadGen.setLeadStat(LeadStatEnum.getByCode(leadStat).getCode());
        updateLeadGen.setFlwpStat(FlwpStatEnum.getByCode(flwpStat).getCode());
        int row = leadGenMapper.updateByPrimaryKey(updateLeadGen);
        log.info("success update row :{}", row);
        return row;
    }
    
    /**
     * 分配询盘单任务
     */
    public int leadgenAllocate(int id, String owner) {
        LeadGen updateLeadGen = leadGenMapper.selectByPrimaryKey(id);
        updateLeadGen.setOwner(owner);
        int row = leadGenMapper.updateByPrimaryKey(updateLeadGen);
        log.info("success update row :{}", row);
        return row;
    }

}
