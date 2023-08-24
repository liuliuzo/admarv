package com.admarv.saas.mail.ui;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.admarv.saas.common.CommonConstant;
import com.admarv.saas.fb.common.Response;
import com.admarv.saas.mail.constant.SMTPEnum;
import com.admarv.saas.mail.domain.EmailReceiveService;
import com.admarv.saas.mail.domain.EmailService;
import com.admarv.saas.mail.dto.req.EmailRequest;
import com.admarv.saas.mail.dto.req.ReqSaveAccountInfo;
import com.admarv.saas.mail.dto.resp.RespEmailMsg;
import com.admarv.saas.mail.dto.resp.RespGetEmailInfo;
import com.admarv.saas.mapper.EmailInfoMapper;
import com.admarv.saas.mapper.EmailMsgMapper;
import com.admarv.saas.model.EmailInfo;
import com.admarv.saas.utils.JacksonUtils;

/**
 * 
 * @author liuliu
 *
 */
@RestController
public class EmailController {
	
	private static final Logger log = LoggerFactory.getLogger(EmailController.class);

	@Autowired
    private EmailService emailService;
	
	@Autowired
	private EmailReceiveService emailReceiveService;
	
	@Autowired
	private EmailInfoMapper emailInfoMapper;
	
	@Autowired
	private EmailMsgMapper emailMsgMapper;
	
    /**
     * 上传邮件附件
     * 
     * @param file
     * @return
     */
    @RequestMapping(value = "/admarv/uploadEmailAttachment", method = RequestMethod.POST)
    public String uploadEmailAttachment(@RequestParam("file") MultipartFile file) {
        log.info("uploadFile size :{}", file.getSize());
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        log.info("fileName:{}", fileName);
        Path path = Paths.get(CommonConstant.EMAIL_FILE_PATH);
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

	@PostMapping("/admarv/sendEmail")
	public Response sendEmail(@RequestBody EmailRequest emailRequest) {
		log.info("/admarv/sendEmail emailRequest:{}", emailRequest);
		String userId = emailRequest.getUserId();
		JavaMailSender javaMailSender = emailService.getJavaMailSender(userId);
		try {
			emailService.sendEmail(javaMailSender, emailRequest);
			Response response = new Response();
			response.setCode("200");
			response.setMessage("Email sent successfully!");
			response.setSuccess(true);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return response;
		} catch (Exception e) {
			Response response = new Response();
			response.setCode("602");
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return response;
		}
	}
	
	@PostMapping("/admarv/saveAccountInfo")
	public Response saveAccountInfo(@RequestBody ReqSaveAccountInfo reqSaveAccountInfo) {
		log.info("/admarv/saveAccountInfo ReqSaveAccountInfo:{}", reqSaveAccountInfo);
		String userId = reqSaveAccountInfo.getUserId();
		String email = reqSaveAccountInfo.getEmail();
		String authCode = reqSaveAccountInfo.getAuthCode();
		boolean valid = emailService.isEmailPasswordValid(email, authCode);
		if (valid) {
			EmailInfo selectEntity = new EmailInfo();
			selectEntity.setUserId(userId);
			EmailInfo emailInfo = emailInfoMapper.selectOneByEntity(selectEntity);
			if (emailInfo != null) {
				if (StringUtils.isNotBlank(email)) {
					emailInfo.setEmail(email);
				}
				if (StringUtils.isNotBlank(authCode)) {
					emailInfo.setAuthCode(authCode);
				}
				emailInfo.setUserId(userId);
				int row = emailInfoMapper.updateByPrimaryKey(emailInfo);
				log.info("success update Email Info row:{}", row);
			} else {
				EmailInfo insert = new EmailInfo();
				insert.setEmail(email);
				insert.setUserId(userId);
				insert.setAuthCode(authCode);
				SMTPEnum smtpEnum = SMTPEnum.getByEmailFormat(email);
				String platform = smtpEnum.getPlatform();
				insert.setPlatform(platform);		
				int row = emailInfoMapper.insert(insert);
				log.info("success insert Email Info row:{}", row);
			}
			Response response = new Response();
			response.setCode("200");
			response.setResult("saveAccountInfo info success!");
			response.setSuccess(true);
			log.info("saveAccountInfo response:{}", response);
			return response;
		} else {
			Response response = new Response();
			response.setCode("604");
			response.setMessage("Email Password Valid Fail");
			response.setSuccess(false);
			log.info("saveAccountInfo response:{}", response);
			return response;
		}
	}
	
	@RequestMapping(value = "/admarv/getEmailInfo", method = RequestMethod.GET)
	public Response getEmailInfo(String userId) {
		log.info("/admarv/getAccountInfo userId:{}", userId);

		EmailInfo selectEmailInfo = new EmailInfo();
		selectEmailInfo.setUserId(userId);
		EmailInfo emailInfo = emailInfoMapper.selectOneByEntity(selectEmailInfo);
		String email = emailInfo.getEmail();
		RespGetEmailInfo respGetEmailInfo = new RespGetEmailInfo();
		respGetEmailInfo.setEmail(email);

		Response response = new Response();
		response.setCode("200");
		response.setResult(respGetEmailInfo);
		response.setSuccess(true);
		log.info("response：{}", response);
		return response;
	}
	
	@RequestMapping(value = "/admarv/emailSync", method = RequestMethod.GET)
	public String emailSync(String userId) {
		log.info("/admarv/emailSync userId:{}", userId);
		EmailInfo selectEmailInfo = new EmailInfo();
		selectEmailInfo.setUserId(userId);
		EmailInfo emailInfo = emailInfoMapper.selectOneByEntity(selectEmailInfo);
		String email = emailInfo.getEmail();
		String password = emailInfo.getPassword();
		String authCode = emailInfo.getAuthCode();
		if (StringUtils.isNotBlank(authCode)) {
			emailReceiveService.receiveEmail(email, authCode);
		} else {
			emailReceiveService.receiveEmail(email, password);
		}
		return "SUCCESS EMAIL SYNC";
	}
	
	@RequestMapping(value = "/admarv/getEmailSync", method = RequestMethod.GET)
	public Response getEmailSync(String userId) {
		log.info("/admarv/getEmailSync userId:{}", userId);
		EmailInfo selectEmailInfo = new EmailInfo();
		selectEmailInfo.setUserId(userId);
		EmailInfo emailInfo = emailInfoMapper.selectOneByEntity(selectEmailInfo);
		log.info("emailInfo:{}", emailInfo);
		String email = emailInfo.getEmail();
		String password = emailInfo.getPassword();
		String authCode = emailInfo.getAuthCode();
		List<RespEmailMsg> result;
		if (StringUtils.isNotBlank(authCode)) {
			result = emailReceiveService.receiveEmail(email, authCode);
		} else {
			result = emailReceiveService.receiveEmail(email, password);
		}
		Response response = new Response();
		response.setCode("200");
		response.setResult(result);
		response.setSuccess(true);
		log.info("response：{}", response);
		return response;
	}
	
}