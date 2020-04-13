package ru.itis.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.itis.dto.MessageDto;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("UserScope")
@Data
public class MessagesList {
    private List<MessageDto> messages;

    public MessagesList() {
        messages = new ArrayList<>();
    }
}
