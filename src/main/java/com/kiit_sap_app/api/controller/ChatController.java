package com.kiit_sap_app.api.controller;


import com.kiit_sap_app.api.entity.ChatMessage;
import com.kiit_sap_app.api.service.ChatService;
import com.kiit_sap_app.api.service.StudentMentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {


    @Autowired
    private ChatService chatService;

    @Autowired
    private StudentMentorService studentMentorService;

    @GetMapping("/fetch")
    private ResponseEntity<?> fetch(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<ChatMessage> chats=chatService.getChats(userName);
        return ResponseEntity.ok(chats);
    }

    @PostMapping("/send")
    private ResponseEntity<?> send(@RequestBody ChatMessage chatMessage){
        /*TODO: set the sender and reciever id from studentmentor and mentorstudent collections
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        ChatMessage message=new ChatMessage();
        message.setSenderRollNo(userName);
        message.setReceiverId(studentMentorService.getMentorIdByStudentId(userName));*/
        try {
            chatService.sendMessage(chatMessage);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
