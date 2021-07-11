package com.openclassroom.payMyBuddy.service;

import java.util.List;

import com.openclassroom.payMyBuddy.model.MessageModel;


public interface IMessageService {

	List<MessageModel> listAllMessages();
	int savedMessage(MessageModel messageModel);
	void deleteMessageById(Long id);
	MessageModel getMessageById(Long id);
	List<MessageModel> findMessageByAuthor(String author);
}
