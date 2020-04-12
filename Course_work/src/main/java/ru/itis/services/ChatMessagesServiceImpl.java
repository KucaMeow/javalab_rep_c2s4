package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.MessageDto;
import ru.itis.models.User;
import ru.itis.repositories.MessagesRepository;
import ru.itis.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatMessagesServiceImpl implements ChatMessagesService {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    MessagesRepository messagesRepository;

    @Override
    public void sendMessage(MessageDto message) {
        messagesRepository.save(message);
        for (User user : usersRepository.findAll()) {
            synchronized (user.getMessages()) {
                user.getMessages().add(message);
                user.getMessages().notifyAll();
            }
        }
    }

    @Override
    public List<MessageDto> sendMessagesToUser (Long userId) {
        Optional<User> userOptional = usersRepository.find(userId);
        if(!userOptional.isPresent()) {
            return null;
        }
        User user = userOptional.get();
        synchronized (user.getMessages()) {
            if (user.getMessages().isEmpty()) {
                try {
                    user.getMessages().wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        List<MessageDto> response = new ArrayList<>(user.getMessages());
        user.getMessages().clear();
        return response;
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
