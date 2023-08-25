package com.admarv.saas.fb.page.ui;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
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
import com.admarv.saas.utils.CronUtils;
import com.admarv.saas.utils.DateUtils;
import com.admarv.saas.utils.JacksonUtils;
import com.admarv.saas.utils.SpringBeanUtils;
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
    public String send(@RequestBody ReqSend reqSend) throws Exception {
        log.info("/send reqSend:{}", reqSend);
        String msg = reqSend.getMsg();
        String pageId = reqSend.getPageId();
        String userId = reqSend.getUserId();
        // 获取user对应的facebookClient
        FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
        
        if (facebookClient == null) {
            Response response = new Response();
            response.setCode("601");
            response.setMessage("此用户未授权");
            response.setSuccess(false);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return srtResponse;
        }
        
        FacebookClient pageAccessClient = facebookClientService.getPageAccessClient(facebookClient, userId);
        List<String> fileNames = reqSend.getFileList();
        // 构建包含多个二进制附件的请求参数
        // List<BinaryAttachment> attachments = new ArrayList<>();
        if (fileNames != null && !fileNames.isEmpty()) {
            List<BinaryAttachment> attachmentList = new ArrayList<>();
            // 动态生成 BinaryAttachment 对象并添加到列表
            for (String fileName : fileNames) {
                attachmentList.add(BinaryAttachment.with(fileName, new FileInputStream(CommonConstant.IMG_POST_PATH + "/" + fileName)));
            }
            Privacy privacy = new Privacy();
            privacy.setValue("EVERYONE");
            Post post = new Post();
            post.setMessage(msg);
            post.setPrivacy(privacy);
            // 将多张照片与消息一起发布到 Facebook
            String photoUrl = "/" + pageId + "/photos";
            log.info("photoUrl:{}", photoUrl);
            FacebookType publishPhotoResponse = pageAccessClient.publish(photoUrl, FacebookType.class, 
            		attachmentList, 
            		Parameter.with("post", post), 
            		Parameter.with("message", msg));
            String id = publishPhotoResponse.getId();
			log.info("id:{}", id);
            return id;
        } else {
            // 如果没有图则只发送文字
            return publishMessage(msg, pageId, pageAccessClient);
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
    public String sendSched(@RequestBody ReqSendSched reqSendSched) {
        log.info("/send reqSendSched:{}", reqSendSched);
        try {
  
			String strSendDt = reqSendSched.getSendDt();
			Date sendDt = DateUtils.stringToDate(strSendDt, DateUtils.PATTERN_DATETIME);
	        String msg = reqSendSched.getMsg();
	        String pageId = reqSendSched.getPageId();
	        String userId = reqSendSched.getUserId();
	        // 获取Spring中的FacebookClientService,获取user对应的facebookClient
	        FacebookClientService facebookClientService = SpringBeanUtils.getBean(FacebookClientService.class);
	        FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
	        FacebookClient pageAccessClient = facebookClientService.getPageAccessClient(facebookClient, userId);
	        
	        
	        
	        
	        
	        
	        
	        
	        List<String> fileNames = reqSendSched.getFileList();
	        try {
	            if (fileNames != null && !fileNames.isEmpty()) {
	                // 创建一个包含多个照片文件输入流的列表
	                List<FileInputStream> imageStreams = new ArrayList<>();
	                for (String fileName : fileNames) {
	                    File imageFile = new File(CommonConstant.IMG_POST_PATH + "/" + fileName);
	                    FileInputStream imageStream = new FileInputStream(imageFile);
	                    imageStreams.add(imageStream);
	                }
	                // 构建包含多个二进制附件的请求参数
	                List<BinaryAttachment> attachments = new ArrayList<>();
	                for (int i = 0; i < imageStreams.size(); i++) {
	                    int imageIndex = i + 1;
	                    BinaryAttachment attachment = BinaryAttachment.with("image" + imageIndex + ".jpg", imageStreams.get(i));
	                    attachments.add(attachment);
	                }
	                Privacy privacy = new Privacy();
	                privacy.setValue("EVERYONE");
	                Post post = new Post();
	                post.setMessage(msg);
	                post.setPrivacy(privacy);
	                // 将多张照片与消息一起发布到 Facebook
	                String photoUrl = "/" + pageId + "/photos";
	                log.info("photoUrl:{}", photoUrl);
	                FacebookType publishPhotoResponse = pageAccessClient.publish(photoUrl, FacebookType.class, attachments, Parameter.with("post", post), Parameter.with("message", msg));
	                String id = publishPhotoResponse.getId();
	                log.info("/id:{}", id);
	                // 关闭输入流
	                for (FileInputStream imageStream : imageStreams) {
	                    imageStream.close();
	                }
	            } else {
	                // 如果没有图则只发送文字
	                publishMessage(msg, pageId, facebookClient);
	            }
	        } catch (Exception e) {
            
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }  	
		log.info("Published message ID:{}", publishMessageResponse.getId());
        
        
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
    
    
    /**
     * publish_video
     */
    @RequestMapping(value = "/admarv/publishVideo", method = RequestMethod.POST)
    public Response publishVideo(@RequestBody ReqSend reqSend) throws Exception {
		return null;
    }
    
}