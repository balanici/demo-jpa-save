package com.balanici.demojpasave.service.impl;

import com.balanici.demojpasave.entity.MessageEntity;
import com.balanici.demojpasave.repository.MessageRepository;
import com.balanici.demojpasave.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageEntity> findAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    @Transactional
    public MessageEntity createMessage(MessageEntity messageEntity) {
        return messageRepository.save(messageEntity);
    }

    @Override
    public MessageEntity findMessageById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Message by id %d not found", messageId)));
    }

    @Override
    public void deleteMessage(MessageEntity messageEntity) {
        messageRepository.delete(messageEntity);
    }

    @Override
    @Transactional
    public void deleteMessageById(Long messageId) {
        messageRepository.delete(findMessageById(messageId));
    }

    /**
     * No need to explicit save,
     * @Transactional will take care
     */
    @Override
    @Transactional
    public MessageEntity changeMessage(Long messageId, String newMessage) {
        MessageEntity messageEntity = findMessageById(messageId);
        messageEntity.setMessage(newMessage);
//        messageRepository.save(messageEntity); //don't meed this, it commits all updates when finishes transaction
        return messageEntity;
    }
}
