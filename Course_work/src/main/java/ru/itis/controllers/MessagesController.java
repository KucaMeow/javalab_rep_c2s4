package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.config.security_details.UserDetailsImpl;
import ru.itis.dto.MessageDto;
import ru.itis.services.ChatMessagesService;

import java.security.Principal;
import java.util.List;

@Controller
public class MessagesController {

    @Autowired
    ChatMessagesService chatMessagesService;

    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message) {
        chatMessagesService.sendMessage(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(Authentication authentication) {
        Long userId = ((UserDetailsImpl) authentication.getDetails()).getUser().getId();
        List<MessageDto> response = chatMessagesService.sendMessagesToUser(userId);
        if(response == null)
            return (ResponseEntity<List<MessageDto>>) ResponseEntity.notFound();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allmessages")
    public ResponseEntity<List<MessageDto>> getAllMessagesForPage() {
        return ResponseEntity.ok(chatMessagesService.sendAllMessages());
    }
}
