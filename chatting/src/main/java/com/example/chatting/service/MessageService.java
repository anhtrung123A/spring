package com.example.chatting.service;

import com.example.chatting.domain.Message;
import com.example.chatting.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomService chatRoomService;

    public Message save(Message message){
        var chatId = chatRoomService.getChatRoomId(message.getSenderId(), message.getRecipientId(), true).orElseThrow();
        message.setChatId(chatId);
        messageRepository.save(message);
        return message;
    }

    public List<Message> findChatMessages(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(messageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
