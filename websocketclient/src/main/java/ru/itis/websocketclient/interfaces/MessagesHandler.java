package ru.itis.websocketclient.interfaces;

import ru.itis.websocketclient.model.Message;

public interface MessagesHandler {
    void handleAcceptCommand (Message message);
    void handleCompleteCommand (Message message);
}
