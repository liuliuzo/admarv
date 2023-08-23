package com.admarv.saas.fb.page.domain;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.admarv.saas.common.CommonConstant;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.page.dto.req.ReqSendSched;
import com.admarv.saas.utils.JacksonUtils;
import com.admarv.saas.utils.SpringBeanUtils;
import com.restfb.BinaryAttachment;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.Privacy;

/**
 * 
 * @author liuliu
 *
 */
public class FBSendSchedTask implements Job {

    private static final Logger log = LoggerFactory.getLogger(FBSendSchedTask.class);

    @Override
    public void execute(JobExecutionContext context) {
        // 获取关联的JobDetail对象
        JobDetail jobDetail = context.getJobDetail();
        // 从JobDetail中获取作业的属性值
        String jobName = jobDetail.getKey().getName();
        String jobGroup = jobDetail.getKey().getGroup();
        Trigger trigger = context.getTrigger();
        String triggerName = trigger.getKey().getName();
        log.info("jobName:{}, jobGroup :{}, trigger:{} execute", jobName, jobGroup, triggerName);
        // 获取作业的参数
        JobDataMap dataMap = jobDetail.getJobDataMap();
        String jsonReqSendSched = dataMap.getString("jsonReqSendSched");
        ReqSendSched reqSendSched = JacksonUtils.fromJson(jsonReqSendSched, ReqSendSched.class);
        log.info("reqSendSched:{}", reqSendSched);
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
        }
    }
    
    private String publishMessage(String msg, String pageId, FacebookClient facebookClient) {
        Privacy privacy = new Privacy();
        privacy.setValue("EVERYONE");
        Post post = new Post();
        post.setMessage(msg);
        post.setPrivacy(privacy);
        log.info("* Feed publishing *:{}", msg);
        String feedUrl = "/" + pageId + "/feed";
        log.info("feedUrl:{}", feedUrl);
        FacebookType publishMessageResponse = facebookClient.publish(feedUrl, FacebookType.class,Parameter.with("post", post), Parameter.with("message", msg));
        String responseId = publishMessageResponse.getId();
        log.info("Published message ID:{}", responseId);
        return responseId;
    }

}
