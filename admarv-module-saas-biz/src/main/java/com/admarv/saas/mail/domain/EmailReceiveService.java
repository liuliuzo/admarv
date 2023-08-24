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
import org.springframework.stereotype.Service;

import com.admarv.saas.mail.constant.POP3Enum;
import com.admarv.saas.mail.dto.resp.RespEmailMsg;
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

	//@Autowired
	//private EmailMsgMapper emailMsgMapper;
	
	/**
	 * @param userName
	 * @param password
	 */
	public List<RespEmailMsg> receiveEmail(final String email, final String password) {
		List<RespEmailMsg> emailMsgList = Lists.newArrayList();
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
			Message[] messages = folder.getMessages(1, folder.getMessageCount());
			
			int newMessageCount = folder.getNewMessageCount();
			log.info("new Message Count :{}", newMessageCount);
			int unreadMessageCount = folder.getUnreadMessageCount();
			log.info("unread Message Count :{}", unreadMessageCount);
			int messageCount = folder.getMessageCount();
			log.info("message Count :{}", messageCount);
			log.info("messages length:{}", messages.length);
			
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				log.info("message:{},i:{}", message, i);			
				log.info("-------------------------");
				String subject = message.getSubject();
				log.info("Subject :{}", subject);
				Address[] fromArray = message.getFrom();	
			    StringBuffer sbFrom = new StringBuffer();
				for (Address address : fromArray) {
					sbFrom.append(address.toString());
				}
				log.info("From :{}", sbFrom.toString());
				log.info("Text :{}", message.getContent().toString());
				Address[] addressArray = message.getAllRecipients();
				StringBuffer sbAddress = new StringBuffer();
				for (Address address : addressArray) {
					sbAddress.append(address.toString());
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
				StringBuffer sbReplayToArray = new StringBuffer();
				for (Address replayTo : replayToArray) {
					log.info("replayTo:{}", replayTo);
					sbAddress.append(sbReplayToArray.toString());
				}
				EmailMsg insert = new EmailMsg();
				insert.setSubject(subject);
				insert.setFrom(sbFrom.toString());
				insert.setText(message.getContent().toString());
				insert.setAddressArray(sbAddress.toString());
				insert.setDescription(description);
				insert.setDisposition(disposition);
				insert.setFileName(fileName);
				insert.setReceivedDate(receivedDate);
				insert.setSentDate(sentDate);
				insert.setReplayToArray(sbReplayToArray.toString());
				
//				int row = emailMsgMapper.insert(insert);
//				log.info("success insert email Msg row:{}", row);
				RespEmailMsg respEmailMsg=new RespEmailMsg();
				respEmailMsg.setEmail(email);
				respEmailMsg.setSubject(subject);
				respEmailMsg.setFrom(fromArray.toString());
				respEmailMsg.setText(message.getContent().toString());
				respEmailMsg.setAddressArray(addressArray.toString());
				respEmailMsg.setDescription(description);
				respEmailMsg.setDisposition(disposition);
				respEmailMsg.setFileName(fileName);
				respEmailMsg.setReceivedDate(receivedDate);
				respEmailMsg.setSentDate(sentDate);
				respEmailMsg.setReplayToArray(replayToArray.toString());
				emailMsgList.add(respEmailMsg);
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
