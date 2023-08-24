package com.admarv.saas.mail.domain;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admarv.saas.mail.constant.POP3Enum;
import com.admarv.saas.mapper.EmailMsgMapper;
import com.admarv.saas.model.EmailMsg;
import com.google.api.client.util.Lists;

/**
 * 
 * @author liuliu
 *
 */
@Service
public class EmailReceiveService {
	
	private static final Logger log = LoggerFactory.getLogger(EmailReceiveService.class);

	@Autowired
	private EmailMsgMapper emailMsgMapper;
	
	/**
	 * @param userName
	 * @param password
	 */
	public List<EmailMsg> receiveEmail(final String email, final String password) {
		List<EmailMsg> emailMsgList = Lists.newArrayList();
		
			Properties properties = new Properties();
			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.debug", Boolean.TRUE);
			properties.put("mail.auth.debug", Boolean.TRUE);
			properties.put("mail.smtp.socketFactory.fallback", "false");
			
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email, password);
				}
			});
			
		 try {
			POP3Enum pop3Enum = POP3Enum.getByEmailFormat(email);
			String host = pop3Enum.getPopHost();
			Store store = session.getStore("pop3");
			store.connect(host, email, password);
			
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			Message[] messages = folder.getMessages();
			log.info("messages length:{}", messages.length);
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				log.info("message:{},i:{}", message, i);			
				log.info("-------------------------");
				String subject = message.getSubject();
				log.info("Subject :{}", subject);
				Address[] fromArray = message.getFrom();
				log.info("From :{}", fromArray.toString());
				log.info("Text :{}", message.getContent().toString());
				Address[] addressArray = message.getAllRecipients();
				for (Address address : addressArray) {
					log.info("address:{}", address);
				}
				String description = message.getDescription();
				log.info("description:{}", description);
				String disposition = message.getDisposition();
				log.info("disposition:{}", disposition);
				String fileName = message.getFileName();
				log.info("fileName:{}", fileName);
				Date receivedDate = message.getReceivedDate();
				log.info("receivedDate:{}", receivedDate);
				Date sentDate = message.getSentDate();
				log.info("sentDate:{}", sentDate);
				Address[] replayToArray = message.getReplyTo();
				for (Address replayTo : replayToArray) {
					log.info("replayTo:{}", replayTo);
				}
				
				EmailMsg insert = new EmailMsg();
				insert.setSubject(subject);
				insert.setFrom(fromArray.toString());
				insert.setText(message.getContent().toString());
				insert.setAddressArray(addressArray.toString());
				insert.setDescription(description);
				insert.setDisposition(disposition);
				insert.setFileName(fileName);
				insert.setReceivedDate(receivedDate);
				insert.setSentDate(sentDate);
				insert.setReplayToArray(replayToArray.toString());
//				int row = emailMsgMapper.insert(insert);
//				log.info("success insert email Msg row:{}", row);
				
				emailMsgList.add(insert);
			}
			
			folder.close(false);
			store.close();
			return emailMsgList;
		} catch (MessagingException | IOException e) {
			log.error("An error occurred: " + e.getMessage());
			return emailMsgList;
		}
	}
	
	public static void main(String[] args) {
		final String userName = "18912602167@163.com";
		final String password = "OJWVJVAYHXGVAKMK";
		EmailReceiveService emailReceiveService = new EmailReceiveService();
		emailReceiveService.receiveEmail(userName, password);
	}
}
