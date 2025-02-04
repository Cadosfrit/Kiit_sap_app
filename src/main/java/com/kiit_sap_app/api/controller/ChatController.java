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

    @PostMapping("/student-send")
    private ResponseEntity<?> studentSend(@RequestBody ChatMessage chatMessage){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        chatMessage.setSenderRollNo(userName);
        chatMessage.setReceiverId(studentMentorService.getMentorIdByStudentId(userName));
        try {
            chatService.sendMessage(chatMessage);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/mentor-send")
    private ResponseEntity<?> mentorSend(@RequestBody ChatMessage chatMessage){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        chatMessage.setSenderRollNo(userName);
        try {
            chatService.sendMessage(chatMessage);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
