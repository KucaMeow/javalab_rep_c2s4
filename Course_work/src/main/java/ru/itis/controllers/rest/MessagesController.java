package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.MessageDto;
import ru.itis.services.ChatMessagesService;

import java.util.List;

@RestController
public class MessagesController {

    @Autowired
    ChatMessagesService chatMessagesService;

    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestParam String message) {
        chatMessagesService.saveMessage(new MessageDto(message));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesForPage() {
        List<MessageDto> response = chatMessagesService.sendMessagesToUser();
        if(response == null)
            return (ResponseEntity<List<MessageDto>>) ResponseEntity.notFound();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allmessages")
    public ResponseEntity<List<MessageDto>> getAllMessagesForPage() {
        return ResponseEntity.ok(chatMessagesService.sendAllMessages());
    }
}
