package com.admarv.saas.fb.page.ui;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.admarv.saas.common.CommonConstant;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.page.domain.FBSendSchedTask;
import com.admarv.saas.fb.page.dto.req.ReqSend;
import com.admarv.saas.fb.page.dto.req.ReqSendSched;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.utils.CronUtils;
import com.admarv.saas.utils.FilesUtils;
import com.admarv.saas.utils.JacksonUtils;
import com.restfb.BinaryAttachment;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.GraphResponse;
import com.restfb.types.Post;
import com.restfb.types.Privacy;

/**
 * facebook 发帖接口
 * 
 * @author liuliu
 *
 */
@RestController
public class FBPageController {
    
    private static final Logger log = LoggerFactory.getLogger(FBPageController.class);
    
    @Autowired
    private FacebookClientService facebookClientService;
    
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    /**
     * 文件上传
     * 
     * @param file
     * @return
     */
    @RequestMapping(value = "/admarv/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("uploadFile size :{}", file.getSize());
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        log.info("fileName:{}", fileName);
        Path path = Paths.get(CommonConstant.IMG_POST_PATH);
        Path resolvedPath = path.resolve(fileName);
        log.info("resolvedPath:{}", resolvedPath);
        try {
            Files.write(resolvedPath, file.getBytes());
        } catch (Exception e) {
            log.error("upload error", e);
            return e.getMessage();
        }
        return fileName;
    }
    
    /**
     * 发送帖子
     * 
     * @param reqSend
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/admarv/send", method = RequestMethod.POST)
    public Response send(@RequestBody ReqSend reqSend) throws Exception {
        log.info("/send reqSend:{}", reqSend);
        String msg = reqSend.getMsg();
        String userId = reqSend.getUserId();
        // 获取user对应的facebookClient
        FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
        
        if (facebookClient == null) {
            Response response = new Response();
            response.setCode("601");
            response.setMessage("此用户未授权");
            response.setSuccess(false);
			log.info("response:{}", response);
            return response;
        }
        
		SysUserFbBind selectEntity = new SysUserFbBind();
		selectEntity.setUserId(userId);
		SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
		if (sysUserFbBind == null) {
			Response response = new Response();
			response.setCode("701");
			response.setMessage("用户未绑定广告账户和公共主页");
			response.setSuccess(false);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return response;
		}
		
		String pageId = sysUserFbBind.getPageId();
        
        FacebookClient pageAccessClient = facebookClientService.getPageAccessClient(facebookClient, userId);
        List<String> fileNames = reqSend.getFileList();
        // 构建包含多个二进制附件的请求参数
        // List<BinaryAttachment> attachments = new ArrayList<>();
        if (fileNames != null && !fileNames.isEmpty()) {
            List<BinaryAttachment> attachmentList = new ArrayList<>();
            // 动态生成 BinaryAttachment 对象并添加到列表
            for (int i = 0; i < fileNames.size(); i++) {
            	String fileName = fileNames.get(i);
            	String filePath = CommonConstant.IMG_POST_PATH + "/" + fileName;
            	byte[] fileBytes = FilesUtils.readFileBytes(filePath);
				attachmentList.add(BinaryAttachment.with("fileName" + i, fileName, fileBytes));
			}
            Privacy privacy = new Privacy();
            privacy.setValue("EVERYONE");
            Post post = new Post();
            
            post.setMessage(msg);
            post.setPrivacy(privacy);
            // 将多张照片与消息一起发布到 Facebook
            String photoUrl = "/" + pageId + "/photos";
            log.info("photoUrl:{}", photoUrl);
            GraphResponse graphResponse = pageAccessClient.publish(photoUrl, GraphResponse.class, 
            		attachmentList, 
            		Parameter.with("message", msg));
            String id = graphResponse.getId();
			log.info("id:{}", id);
            Response response = new Response();
            response.setCode("200");
            response.setResult(id);
            response.setSuccess(true);
			log.info("response:{}", response);
            return response;
        } else {
            // 如果没有图则只发送文字
			String id = this.publishMessage(msg, pageId, pageAccessClient);
        	Response response = new Response();
            response.setCode("200");
            response.setResult(id);
            response.setSuccess(true);
   			log.info("response:{}", response);
            return response;
        }
    }
    
    /**
     * 定时发送贴文
     * 
     * TODO: 需要持久化scheduler任务重新启动后重新加载，后续改用SpringBatch替换优化
     * 
     * @param reqSend
     * @return
     * @throws Exception
     */
    @PostMapping("/admarv/sendSched")
    public String add(@RequestBody ReqSendSched reqSendSched) {
        log.info("/send reqSendSched:{}", reqSendSched);
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // 定义JobDetail
            String jsonReqSendSched = JacksonUtils.toJson(reqSendSched);
            JobDetail fbSendSchedTaskJobDetail = JobBuilder.newJob(FBSendSchedTask.class)
                    .withIdentity("FBSendSchedTask", "GroupFBSendSched")
                    .usingJobData("jsonReqSendSched", jsonReqSendSched).build();
            String sendDt = reqSendSched.getSendDt();
            // 使用CRON表达式设置定时任务
            String cronDt = CronUtils.convertToCronExpression(sendDt);
            // 定义触发器
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(UUID.randomUUID().toString(), "GroupFBSendSched")
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronDt)).build();
            // 将作业和触发器注册到调度器中
            scheduler.scheduleJob(fbSendSchedTaskJobDetail, trigger);
            // 启动调度器
            scheduler.start();
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    private String publishMessage(String original, String pageId, FacebookClient pageAccessClient) {
        Privacy privacy = new Privacy();
        privacy.setValue("EVERYONE");
        Post post = new Post();
        post.setMessage(original);
        post.setPrivacy(privacy);
        log.info("* Feed publishing *:{}", original);
        String feedUrl = "/" + pageId + "/feed";
        log.info("feedUrl:{}", feedUrl);
        FacebookType publishMessageResponse = pageAccessClient.publish(feedUrl, FacebookType.class, 
        		Parameter.with("post", post),
        		Parameter.with("message", original));
        String responseId = publishMessageResponse.getId();
        log.info("Published message ID:{}", responseId);
        return responseId;
    }
    
}