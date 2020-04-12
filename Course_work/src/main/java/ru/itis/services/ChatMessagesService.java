package ru.itis.services;

import ru.itis.dto.MessageDto;

import java.util.List;

public interface ChatMessagesService {
    void sendMessage(MessageDto message);
    List<MessageDto> sendMessagesToUser(Long userId);
    List<MessageDto> sendAllMessages();
}
