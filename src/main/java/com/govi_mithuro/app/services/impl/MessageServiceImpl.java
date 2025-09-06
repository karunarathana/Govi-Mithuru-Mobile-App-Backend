package com.govi_mithuro.app.services.impl;

import com.govi_mithuro.app.dto.MessageDto;
import com.govi_mithuro.app.model.MessageEntity;
import com.govi_mithuro.app.repo.MessageRepo;
import com.govi_mithuro.app.services.MessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;

    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public String createMessage(MessageDto messageDto) {
        MessageEntity message = convertDTOMessageRepo(messageDto);
        messageRepo.save(message);
        return "Message Save Successfully";
    }

    @Override
    public List<MessageEntity> viewMessage() {
        List<MessageEntity> allMessage = messageRepo.findAll();
        return allMessage;
    }

    @Override
    public String deleteMessage(int messageId) {
        messageRepo.deleteById(messageId);
        return "Message Deleted Successfully";
    }

    private MessageEntity convertDTOMessageRepo(MessageDto messageDto){
        MessageEntity message = new MessageEntity();
        message.setMessageBody(messageDto.getMessageBody());
        message.setMessageSubject(messageDto.getMessageSubject());
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter); // "06-09-2025"
        message.setMessageDate(formattedDate);
        return message;
    }
}
