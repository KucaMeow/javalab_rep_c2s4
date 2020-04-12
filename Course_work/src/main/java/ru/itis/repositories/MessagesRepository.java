package ru.itis.repositories;

import ru.itis.dto.MessageDto;
import ru.itis.models.Message;

public interface MessagesRepository extends CrudRepository<Long, Message> {
    void save(MessageDto message);
}
