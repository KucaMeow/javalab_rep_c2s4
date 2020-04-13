package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ru.itis.dto.MessageDto;
import ru.itis.models.MessagesList;
import ru.itis.repositories.MessagesRepository;
import ru.itis.scopes.UserScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@SessionScope
public class ChatMessagesServiceImpl implements ChatMessagesService {

    @Autowired
    MessagesRepository messagesRepository;
    @Autowired
    MessagesList messagesList;
    @Autowired
    ApplicationContext context;

    @Override
    public void saveMessage(MessageDto message) {
        messagesRepository.save(message);
        for (Map<String, Object> map : UserScope.objects.values()) {
            MessagesList ml = (MessagesList) map.get("messagesList");
            synchronized (ml.getMessages()) {
                ml.getMessages().add(message);
                ml.getMessages().notifyAll();
            }
        }
    }

    @Override
    public List<MessageDto> sendMessagesToUser () {
        synchronized (messagesList.getMessages()) {
            if (messagesList.getMessages().isEmpty()) {
                try {
                    messagesList.getMessages().wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
            List<MessageDto> response = new ArrayList<>(messagesList.getMessages());
            messagesList.getMessages().clear();
            return response;
        }
    }

    @Override
    public List<MessageDto> sendAllMessages() {
        return messagesRepository
                .findAll()
                .stream()
                .map(m -> new MessageDto(m.getMessage()))
                .collect(Collectors.toList());
    }
}
