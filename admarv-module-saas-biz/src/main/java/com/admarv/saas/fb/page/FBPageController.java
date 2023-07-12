package com.admarv.saas.fb.page;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.admarv.saas.fb.common.CommonConstant;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.page.dto.req.ReqGetGPTMsg;
import com.admarv.saas.fb.page.dto.req.ReqSend;
import com.admarv.saas.fb.page.dto.req.ReqSendSched;
import com.admarv.saas.fb.page.dto.resp.Pages;
import com.admarv.saas.utils.CronUtils;
import com.admarv.saas.utils.JacksonUtils;
import com.google.common.collect.Lists;
import com.restfb.BinaryAttachment;
import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.Privacy;
import com.restfb.types.User;

import io.github.flashvayne.chatgpt.service.ChatgptService;

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
    private ChatgptService chatgptServic;
    
    /**
     * GPT贴文生成接口
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
        StringBuilder builder = new StringBuilder();
        // TODO: 获取user基础属性埋点数据
        String userMsgTemplate = "";
        builder.append(userMsgTemplate);
        builder.append(reqGetGPTMsg.getMsg());
        
        String result = chatgptServic.sendMessage(builder.toString());
        log.info("result:{}", result);
        return result;
    }
    
    @RequestMapping(value = "/admarv/pages", method = RequestMethod.GET)
    public String pages(String user) {
        log.info("/admarv/pages:{}", user);
        FacebookClient facebookClient = facebookClientService.getClientByUser(user);
        // 获取登录用户的信息
        User me = facebookClient.fetchObject("me", User.class);
        // 获取用户管理的所有页面和应用程序
        Connection<Page> accounts = facebookClient.fetchConnection(me.getId() + "/accounts", Page.class);
        List<Page> pageList = accounts.getData();
        List<Pages> listRespPages = Lists.newArrayList();
        for (Page page : pageList) {
            String pageId = page.getId();
            String pageName = page.getName();
            log.info("pageId:{}, pageName:{}", pageId, pageName);
            Pages pages = new Pages();
            pages.setPageId(pageId);
            pages.setPageName(pageName);
            listRespPages.add(pages);
        }
        String result = JacksonUtils.toJson(listRespPages);
        log.info("result:{}", result);
        return result;
    }
    
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
        Path path = Paths.get(CommonConstant.FILE_PATH);
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
    public String add(@RequestBody ReqSend reqSend) throws Exception {
        log.info("/send reqSend:{}", reqSend);
        String msg = reqSend.getMsg();
        String pageId = reqSend.getPageId();
        String user = reqSend.getUser();
        // 获取user对应的facebookClient
        FacebookClient facebookClient = facebookClientService.getClientByUser(user);
        FacebookClient pageAccessClient = facebookClientService.getPageAccessClient(facebookClient, pageId);
        List<String> fileNames = reqSend.getFileList();
        if (fileNames != null && !fileNames.isEmpty()) {
            // 创建一个包含多个照片文件输入流的列表
            List<FileInputStream> imageStreams = new ArrayList<>();
            for (String fileName : fileNames) {
                File imageFile = new File(CommonConstant.FILE_PATH + "/" + fileName);
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
            FacebookType publishPhotoResponse = pageAccessClient.publish(photoUrl, FacebookType.class, attachments,Parameter.with("post", post),Parameter.with("message", msg));
            String id = publishPhotoResponse.getId();
            log.info("/id:{}", id);
            // 关闭输入流
            for (FileInputStream imageStream : imageStreams) {
                imageStream.close();
            }
            return id;
        } else {
            // 如果没有图则只发送文字
            return publishMessage(msg, pageId, facebookClient);
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
    @RequestMapping(value = "/admarv/sendSched", method = RequestMethod.POST)
    public String add(@RequestBody ReqSendSched reqSendSched) {
        log.info("/send reqSendSched:{}", reqSendSched);
        try {
            // 创建调度器
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
    
    private String publishMessage(String original, String pageId, FacebookClient facebookClient) {
        FacebookClient pageAccessClient = facebookClientService.getPageAccessClient(facebookClient, pageId);
        Privacy privacy = new Privacy();
        privacy.setValue("EVERYONE");
        Post post = new Post();
        post.setMessage(original);
        post.setPrivacy(privacy);
        log.info("* Feed publishing *:{}", original);
        String feedUrl = "/" + pageId + "/feed";
        log.info("feedUrl:{}", feedUrl);
        FacebookType publishMessageResponse = pageAccessClient.publish(feedUrl, FacebookType.class, Parameter.with("post", post),Parameter.with("message", original));
        String responseId = publishMessageResponse.getId();
        log.info("Published message ID:{}", responseId);
        return responseId;
    }
}