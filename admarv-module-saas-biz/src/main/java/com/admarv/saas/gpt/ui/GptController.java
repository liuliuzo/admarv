package com.admarv.saas.gpt.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.Response;
import com.admarv.saas.gpt.dto.req.ReqGetGPTMsg;
import com.admarv.saas.mapper.GptMsgMapper;
import com.admarv.saas.model.GptMsg;
import com.admarv.saas.utils.JacksonUtils;

import io.github.flashvayne.chatgpt.service.ChatgptService;

/**
 * GPT 服务
 * 
 * @author liuliu
 *
 */
@RestController
public class GptController {
    
    private static final Logger log = LoggerFactory.getLogger(GptController.class);
    
    @Autowired
    private ChatgptService chatgptServic;
    
    @Autowired
    private GptMsgMapper gptMsgMapper;
    
    /**
     * GPT贴文生成接口
     * 
     * 1. 获取user基础属性埋点数据
     * 2. 合并提交贴文问题
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/admarv/getGPTTemplate", method = RequestMethod.POST)
    public String getGPTTemplate(@RequestBody ReqGetGPTMsg reqGetGPTMsg) {
        log.info("/admarv/getGPTMsg reqGetGPTMsg:{}", reqGetGPTMsg);
        StringBuilder builder = new StringBuilder();
		String gnrtPost = "请根以下信息生成一篇英文的营销贴文:";
		builder.append(gnrtPost);
        builder.append(reqGetGPTMsg.getMsg());
        String result = chatgptServic.sendMessage(builder.toString());
        log.info("result:{}", result);
        Response response = new Response();
        response.setCode("200");
        response.setResult(result);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }

    /**
     * GPT普通聊天
     * 
     * 1. 获取user基础属性埋点数据
     * 2. 合并提交贴文问题
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/admarv/getGPTMsg", method = RequestMethod.POST)
    public String getGPTMsg(@RequestBody ReqGetGPTMsg reqGetGPTMsg) {
        log.info("/admarv/getGPTMsg reqGetGPTMsg:{}", reqGetGPTMsg);
		String userId = reqGetGPTMsg.getUserId();
		String conversationId = reqGetGPTMsg.getConversationId();
		String msg = reqGetGPTMsg.getMsg();
		
		GptMsg insertUer = new GptMsg();
		insertUer.setUserId(userId);
		insertUer.setConversationId(conversationId);
		insertUer.setMsg(msg);
		int row = gptMsgMapper.insert(insertUer);
		log.info("insert gpt_msg success row:{}, insertUer:{}", row, insertUer);
		
        StringBuilder builder = new StringBuilder();
        builder.append(reqGetGPTMsg.getMsg());
        
		GptMsg selectEnity = new GptMsg();
		selectEnity.setUserId(userId);
		selectEnity.setConversationId(conversationId);
		List<GptMsg> record = gptMsgMapper.selectByEntity(selectEnity);
		if (!CollectionUtils.isEmpty(record)) {
			for (GptMsg gptMsg : record) {
				log.info("gptMsg:{}", gptMsg);
				builder.append(" ").append(gptMsg.getMsg());
			}
		}
		
		//请求消息
        String result = chatgptServic.sendMessage(builder.toString());
        log.info("result:{}", result);
        
		GptMsg insertGpt = new GptMsg();
		insertGpt.setUserId("gpt_001");
		insertGpt.setConversationId(conversationId);
		insertGpt.setMsg(result);
		int row2 = gptMsgMapper.insert(insertGpt);
		log.info("insert gpt_msg success row:{},insertGpt:{}", row2, insertGpt);
		
        Response response = new Response();
        response.setCode("200");
        response.setResult(result);
        response.setSuccess(true);
        String srtResponse = JacksonUtils.toJson(response);
        log.info(srtResponse);
        return srtResponse;
    }
}