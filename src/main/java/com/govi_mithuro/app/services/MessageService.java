package com.govi_mithuro.app.services;

import com.govi_mithuro.app.dto.MessageDto;
import com.govi_mithuro.app.model.MessageEntity;

import java.util.List;

public interface MessageService {
    String createMessage(MessageDto messageDto);
    List<MessageEntity> viewMessage();
    String deleteMessage(int messageId);
}
