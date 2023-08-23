package com.admarv.saas.utils;

import java.util.ArrayList;
import java.util.List;

import com.admarv.saas.chat.whatsapp.model.Contacts;
import com.admarv.saas.chat.whatsapp.model.Messages;

/**
 * 
 * @author liuliu
 *
 */
public class MatchedPair {

	private Messages messages;
	private Contacts contacts;

	public MatchedPair(Messages messages, Contacts contacts) {
		this.messages = messages;
		this.contacts = contacts;
	}

	public Messages getMessages() {
		return messages;
	}

	public Contacts getContacts() {
		return contacts;
	}

	public static List<MatchedPair> findMatchedPairs(List<Messages> messagesList, List<Contacts> contactsList) {
		List<MatchedPair> matchedPairs = new ArrayList<>();
		for (Messages messages : messagesList) {
			for (Contacts contacts : contactsList) {
				if (messages.getFrom().equals(contacts.getWaId())) {
					matchedPairs.add(new MatchedPair(messages, contacts));
				}
			}
		}
		return matchedPairs;
	}
}
