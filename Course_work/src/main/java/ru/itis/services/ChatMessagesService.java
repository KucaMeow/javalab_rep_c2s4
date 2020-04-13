package ru.itis.services;

import ru.itis.dto.MessageDto;

import java.util.List;

public interface ChatMessagesService {
    void saveMessage(MessageDto message);
    List<MessageDto> sendMessagesToUser();
    List<MessageDto> sendAllMessages();
}
