package com.admarv.saas.fb.page.ui;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.admarv.saas.common.CommonConstant;
import com.admarv.saas.fb.common.FacebookClientService;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.page.dto.req.ReqPublishVideo;
import com.admarv.saas.fb.page.dto.req.ReqPublishVideoScheduler;
import com.admarv.saas.fb.page.dto.resp.RespPublishVideo;
import com.admarv.saas.utils.FilesUtils;
import com.admarv.saas.utils.JacksonUtils;
import com.restfb.BinaryAttachment;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.GraphResponse;

/**
 * facebook 视频发帖接口
 * 
 * @author liuliu
 *
 */
@RestController
public class FBPagePostController {
    
    private static final Logger log = LoggerFactory.getLogger(FBPagePostController.class);
    
    @Autowired
    private FacebookClientService facebookClientService;
    
    /**
     * publish_video
     */
    @RequestMapping(value = "/admarv/publishVideo", method = RequestMethod.POST)
    public Response publishVideo(@RequestBody ReqPublishVideo reqPublishVideo) throws Exception {
		log.info("/admarv/publishVideo reqPublishVideo:{}", reqPublishVideo);
		String userId = reqPublishVideo.getUserId();
		String fileName = reqPublishVideo.getFileName();
		String msg = reqPublishVideo.getMsg();
		
		FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
		if (facebookClient == null) {
            Response response = new Response();
            response.setCode("601");
            response.setMessage("此用户未授权");
            response.setSuccess(false);
            String srtResponse = JacksonUtils.toJson(response);
            log.info(srtResponse);
            return response;
        }
		
		try {
			FacebookClient pageAccessClient = facebookClientService.getPageAccessClient(facebookClient, userId);
			String filePath = CommonConstant.VIDEO_POST_PATH + "/" + fileName;
			log.info("filePath:{}", filePath);
			byte[] fileBytes = FilesUtils.readFileBytes(filePath);
			GraphResponse publishPhotoResponse = pageAccessClient.publish("me/videos", GraphResponse.class,
					BinaryAttachment.with("source", fileName, fileBytes), 
					Parameter.with("description", msg));
			
			String id = publishPhotoResponse.getId();
			String postId = publishPhotoResponse.getPostId();
			String timelineId = publishPhotoResponse.getTimelineId();
			RespPublishVideo respPublishVideo = new RespPublishVideo();
			respPublishVideo.setId(id);
			respPublishVideo.setPostId(postId);
			respPublishVideo.setTimelineId(timelineId);
			
			Response response = new Response();
			response.setCode("200");
			response.setResult(respPublishVideo);
			response.setSuccess(true);
			log.info("response:{}", response);
			return response;
		} catch (Exception e) {
			log.error("publishVideo error", e);
			Response response = new Response();
			response.setCode("701");
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			log.info("response:{}", response);
			return response;
		}
    }
    
    /**
     * publish_video
     */
    @RequestMapping(value = "/admarv/publishVideoScheduler", method = RequestMethod.POST)
    public Response publishVideoScheduler(@RequestBody ReqPublishVideoScheduler reqPublishVideoScheduler) throws Exception {
		log.info("/admarv/publishVideo reqPublishVideoScheduler:{}", reqPublishVideoScheduler);
		String userId = reqPublishVideoScheduler.getUserId();
		String fileName = reqPublishVideoScheduler.getFileName();
		String msg = reqPublishVideoScheduler.getMsg();
		Date sendDate = reqPublishVideoScheduler.getSendDate();
		FacebookClient facebookClient = facebookClientService.getClientByUserId(userId);
		if (facebookClient == null) {
			Response response = new Response();
			response.setCode("601");
			response.setMessage("此用户未授权");
			response.setSuccess(false);
			log.info("response:{}", response);
			return response;
		}
		
		try {
			FacebookClient pageAccessClient = facebookClientService.getPageAccessClient(facebookClient, userId);
			String filePath = CommonConstant.VIDEO_POST_PATH + "/" + fileName;
			log.info("filePath:{}", filePath);
			byte[] fileBytes = FilesUtils.readFileBytes(filePath);
			GraphResponse publishPhotoResponse = pageAccessClient.publish("me/videos", GraphResponse.class,
					BinaryAttachment.with("source", fileName, fileBytes), 
					Parameter.with("description", msg),
					Parameter.with("published", false), 
					Parameter.with("scheduled_publish_time", sendDate)
					);
			String id = publishPhotoResponse.getId();
			String postId = publishPhotoResponse.getPostId();
			String timelineId = publishPhotoResponse.getTimelineId();
			
			RespPublishVideo respPublishVideo = new RespPublishVideo();
			respPublishVideo.setId(id);
			respPublishVideo.setPostId(postId);
			respPublishVideo.setTimelineId(timelineId);
			
			Response response = new Response();
			response.setCode("200");
			response.setResult(respPublishVideo);
			response.setSuccess(true);
			log.info("response:{}", response);
			return response;
		} catch (Exception e) {
			log.error("publishVideo error", e);
			Response response = new Response();
			response.setCode("701");
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			log.info("response:{}", response);
			return response;
		}
    }
    
    /**
     * 视频文件上传
     * 
     * @param file
     * @return
     */
    @RequestMapping(value = "/admarv/uploadVideo", method = RequestMethod.POST)
    public String uploadVideo(@RequestParam("file") MultipartFile file) {
        log.info("uploadVideo size :{}", file.getSize());
		if (file.isEmpty()) {
			return "Please select a file to upload";
		}
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        log.info("fileName:{}", fileName);
        Path path = Paths.get(CommonConstant.VIDEO_POST_PATH);
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
    
}