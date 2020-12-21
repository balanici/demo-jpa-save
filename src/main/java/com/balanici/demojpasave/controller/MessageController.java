package com.balanici.demojpasave.controller;

import com.balanici.demojpasave.entity.MessageEntity;
import com.balanici.demojpasave.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageEntity>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAllMessages());
    }

    @PostMapping
    public ResponseEntity<MessageEntity> postMessage(@RequestBody MessageEntity messageEntity) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(messageService.createMessage(messageEntity));
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable(name = "messageId") Long messageId) {
        messageService.deleteMessageById(messageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageEntity> getMessageById(@PathVariable(name = "messageId") Long messageId) {
        return ResponseEntity.ok(messageService.findMessageById(messageId));
    }

    @PostMapping("/{messageId}")
    public ResponseEntity<MessageEntity> changeMessage(@PathVariable(name = "messageId") Long messageId,
                                                       @RequestBody String newMessage) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(messageService.changeMessage(messageId, newMessage));
    }


}
