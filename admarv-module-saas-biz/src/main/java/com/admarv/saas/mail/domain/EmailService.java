package com.admarv.saas.mail.domain;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.admarv.saas.common.CommonConstant;
import com.admarv.saas.mail.constant.SMTPEnum;
import com.admarv.saas.mail.dto.req.EmailRequest;
import com.admarv.saas.mapper.EmailInfoMapper;
import com.admarv.saas.model.EmailInfo;

/**
 * 
 * @author liuliu
 *
 */
@Service
public class EmailService {
	
	private static final Logger log = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	private EmailInfoMapper emailInfoMapper;

	public boolean isEmailPasswordValid(String email, String password) {
		SMTPEnum smtpServerEnum = SMTPEnum.getByEmailFormat(email);
		String host = smtpServerEnum.getSmtpHost();
		int port = smtpServerEnum.getSmtpPort();
		//int sslPort = smtpServerEnum.getSmtpSslPort();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.enable", "false");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "false");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() { 
            	return new PasswordAuthentication(email, password);
            }
        });
        
        try {
            session.getTransport("smtp").connect();
            log.info("valid success");
            return true; 
        } catch (AuthenticationFailedException e) {
        	log.error("Password Valid Fail!");
            return false;
        } catch (MessagingException e) {
			log.error("valid fail", e);
            return false;
        }
    }

	/**
	 * 无文件
	 * 
	 * @param javaMailSender
	 * @param emailRequest
	 * @throws Exception 
	 */
	public void sendEmail(JavaMailSender javaMailSender, EmailRequest emailRequest) throws Exception {
		log.info("sendEmail emailRequest:{}", emailRequest);
		String userId = emailRequest.getUserId();
		String to = emailRequest.getTo();
		String subject = emailRequest.getSubject();
		String text = emailRequest.getText();
		String cc = emailRequest.getCc();
		String bcc = emailRequest.getBcc();
		
		EmailInfo selectEmailInfo = new EmailInfo();
		selectEmailInfo.setUserId(userId);
		EmailInfo emailInfo = emailInfoMapper.selectOneByEntity(selectEmailInfo);
		log.info("emailInfo:{}", emailInfo);
		String email = emailInfo.getEmail();
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true); // Enable HTML content
		helper.setFrom(email);
		helper.setBcc(bcc);
		helper.setCc(cc);
		
		//支持多个邮件
		if (to.contains(";")) {
			String[] addess = to.split(";");
			helper.setTo(addess);
		} else {
			helper.setTo(to);
		}
		
		helper.setSubject(subject);
		helper.setText(text, true); // Set HTML content;
		List<String> attachments = emailRequest.getAttachments();
	    //如果没有文件
		if (CollectionUtils.isEmpty(attachments)) {
			javaMailSender.send(message);
		} else {
	    //如果有文件
			for (String fileName : attachments) {
				File file = new File(CommonConstant.EMAIL_FILE_PATH + fileName);
				helper.addAttachment(fileName, file);
			}
			javaMailSender.send(message);
		}
    }
	
    public JavaMailSender getJavaMailSender(String userId) {
    	EmailInfo selectEmailInfo = new EmailInfo();
    	selectEmailInfo.setUserId(userId);
    	
		EmailInfo emailInfo = emailInfoMapper.selectOneByEntity(selectEmailInfo);
		log.info("emailInfo:{}", emailInfo);
		String email = emailInfo.getEmail();
		String password = emailInfo.getPassword();
		String authCode = emailInfo.getAuthCode();
		
		SMTPEnum smtpServerEnum = SMTPEnum.getByEmailFormat(email);
		String host = smtpServerEnum.getSmtpHost();
		int port = smtpServerEnum.getSmtpPort();
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(email);
		if (StringUtils.isNotBlank(authCode)) {
			mailSender.setPassword(authCode);
		} else {
			mailSender.setPassword(password);
		}
 
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.ssl.enable", "false");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.connectiontimeout", "5000");
		props.put("mail.smtp.timeout", "5000");
		props.put("mail.debug", "false");
		
        return mailSender;
    }
}

