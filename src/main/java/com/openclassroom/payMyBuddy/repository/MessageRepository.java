package com.openclassroom.payMyBuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassroom.payMyBuddy.model.MessageModel;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Long> {

	List<MessageModel> findByAuthor(String author);

}
