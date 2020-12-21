package com.balanici.demojpasave.service;

import com.balanici.demojpasave.entity.MessageEntity;

import java.util.List;

public interface MessageService {

    List<MessageEntity> findAllMessages();

    MessageEntity createMessage(MessageEntity messageEntity);

    MessageEntity findMessageById(Long messageId);

    void deleteMessage(MessageEntity messageEntity);

    void deleteMessageById(Long messageId);

    MessageEntity changeMessage(Long messageId, String newMessage);
}
