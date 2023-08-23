package com.admarv.saas.fb.lead.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.Response;
import com.admarv.saas.mapper.TrackOfBehaviorMapper;
import com.admarv.saas.model.TrackOfBehavior;
import com.admarv.saas.utils.JacksonUtils;

/**
 * 
 * @author liuliu
 *
 */
@RestController
public class TrackOfBehaviorController {

    private static final Logger log = LoggerFactory.getLogger(TrackOfBehaviorController.class);

    @Autowired
    private TrackOfBehaviorMapper trackOfBehaviorMapper;

    @GetMapping("/admarv/behaviorLog")
    public String leadgenDetail(String leadId) {
        log.info("/admarv/leadgenDetail leadId:{}", leadId);
        TrackOfBehavior selectEntity = new TrackOfBehavior();
        selectEntity.setLeadId(leadId);
        List<TrackOfBehavior> trackOfBehaviorList = trackOfBehaviorMapper.selectByEntity(selectEntity);
        log.info("trackOfBehaviorList:{}", trackOfBehaviorList);
        Response response = new Response();
        response.setCode("200");
        response.setResult(trackOfBehaviorList);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }

}
