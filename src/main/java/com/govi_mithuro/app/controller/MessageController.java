package com.govi_mithuro.app.controller;

import com.govi_mithuro.app.constant.APIConstants;
import com.govi_mithuro.app.dto.MessageDto;
import com.govi_mithuro.app.model.MessageEntity;
import com.govi_mithuro.app.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstants.API_ROOT)
@CrossOrigin
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = APIConstants.ADD_NEW_MESSAGE, method = RequestMethod.POST)
    public ResponseEntity<?> createMessage(@RequestBody MessageDto messageDto) {
        logger.info("Request Started In createMessage |UserDto={} ", messageDto);
        String response = messageService.createMessage(messageDto);
        logger.info("Request Completed In createMessage |response={} ", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = APIConstants.VIEW_MESSAGE, method = RequestMethod.GET)
    public ResponseEntity<?> viewMessage() {
        logger.info("Request Started In viewMessage ");
        List<MessageEntity> response = messageService.viewMessage();
        logger.info("Request Completed In viewMessage |response={} ", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = APIConstants.DELETE_MESSAGE, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMessage(@RequestParam("messageId") int messageId) {
        logger.info("Request Started In deleteMessage |messageId={} ", messageId);
        String response = messageService.deleteMessage(messageId);
        logger.info("Request Completed In deleteMessage |response={} ", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
