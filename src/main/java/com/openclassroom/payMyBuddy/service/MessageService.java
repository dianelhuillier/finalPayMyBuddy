package com.openclassroom.payMyBuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassroom.payMyBuddy.model.MessageModel;
import com.openclassroom.payMyBuddy.repository.MessageRepository;

@Service
public class MessageService implements IMessageService{
	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public List<MessageModel> listAllMessages() {
		return messageRepository.findAll();
	}

	@Override
	public int savedMessage(MessageModel message) {
		MessageModel ms = messageRepository.save(message);
		if(ms != null){
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public void deleteMessageById(Long id) {
		 messageRepository.deleteById(id);
	}

	@Override
	public MessageModel getMessageById(Long id) {
		return messageRepository.getOne(id);
	}

	@Override
	public List<MessageModel> findMessageByAuthor(String author) {
		return messageRepository.findByAuthor(author);
	}


}
