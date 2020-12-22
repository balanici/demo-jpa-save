package com.balanici.demojpasave.service.impl;

import com.balanici.demojpasave.entity.MessageEntity;
import com.balanici.demojpasave.repository.MessageRepository;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class MessageServiceTest {

    private MessageEntity messageEntity;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @BeforeEach
    void setUp() {
        messageEntity = MessageEntity.builder()
                .id(1L)
                .message("Test message")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllMessages() {
        //given
        val expected = List.of(messageEntity);
        when(messageRepository.findAll()).thenReturn(expected);

        //when
        List<MessageEntity> result = messageService.findAllMessages();

        //then
        assertEquals(expected, result);
    }

    @Test
    void createMessage() {
        when(messageRepository.save(messageEntity)).thenReturn(messageEntity);
        val result = messageService.createMessage(messageEntity);
        assertEquals(messageEntity, result);
    }

    @Test
    void findMessageById() {
        when(messageRepository.findById(anyLong())).thenReturn(Optional.of(messageEntity));
        val result = messageService.findMessageById(1L);
        assertEquals(messageEntity, result);
    }

    @Test
    void deleteMessage() {
        doNothing().when(messageRepository).delete(messageEntity);
        messageService.deleteMessage(messageEntity);
        verify(messageRepository).delete(messageEntity);
    }

    @Test
    void deleteMessageById() {
        when(messageRepository.findById(1L)).thenReturn(Optional.of(messageEntity));
        doNothing().when(messageRepository).delete(messageEntity);
        messageService.deleteMessageById(1L);
        verify(messageRepository).delete(messageEntity);
    }

    @Test
    void changeMessage() {
        String newMessage = "New Message";
        when(messageRepository.findById(1L)).thenReturn(Optional.of(messageEntity));
        val result = messageService.changeMessage(1L, newMessage);
        assertEquals(newMessage, result.getMessage());
    }
}