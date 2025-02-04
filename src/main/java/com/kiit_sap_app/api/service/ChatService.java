package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.ChatMessage;
import com.kiit_sap_app.api.entity.UserEntity;
import com.kiit_sap_app.api.repository.ChatMessageRepository;
import com.kiit_sap_app.api.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves the list of chat messages for the given username.
     *
     * @param username the user's username (for example, their roll number or faculty id)
     * @return a list of ChatMessage objects that the user is involved in.
     */
    public List<ChatMessage> getChats(String username) {
        // Look up the UserEntity by username
        Optional<UserEntity> userOpt = Optional.ofNullable(userRepository.findByUsername(username));
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        UserEntity user = userOpt.get();

        // Fetch all ChatMessages using the stored chat IDs
        List<ChatMessage> chatMessages = chatMessageRepository.findAllByIdIn(user.getChatIds());

        // Find messages where the user is the receiver (mentorFacultyId matches username)
        List<ChatMessage> unreadMessages = chatMessages.stream()
                .filter(chat -> chat.getReceiverId().equals(username) && !chat.isRead())
                .toList();

        // Mark them as read
        if (!unreadMessages.isEmpty()) {
            unreadMessages.forEach(chat -> chat.setRead(true));
            chatMessageRepository.saveAll(unreadMessages);
        }

        return chatMessages;
    }


    /**
     * Saves a chat message and updates both the sender's and receiver's (mentor's) UserEntity.
     *
     * @param chatMessage the ChatMessage object to save.
     * @throws IllegalArgumentException if the sender or receiver cannot be found.
     */
    public void sendMessage(ChatMessage chatMessage) {
        if (chatMessage.getTimestamp() == null) {
            chatMessage.setTimestamp(LocalDateTime.now());
        }
        // Save the chat message to the collection.
        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);
        ObjectId chatId = savedMessage.getId();

        // Update the sender's UserEntity.
        Optional<UserEntity> senderOpt = Optional.ofNullable(userRepository.findByUsername(chatMessage.getSenderRollNo()));
        if (!senderOpt.isPresent()) {
            throw new IllegalArgumentException("Sender not found: " + chatMessage.getSenderRollNo());
        }
        UserEntity sender = senderOpt.get();
        sender.getChatIds().add(chatId);
        userRepository.save(sender);

        // Update the receiver's (mentor's) UserEntity.
        Optional<UserEntity> receiverOpt = Optional.ofNullable(userRepository.findByUsername(chatMessage.getReceiverId()));
        if (!receiverOpt.isPresent()) {
            throw new IllegalArgumentException("Receiver not found: " + chatMessage.getReceiverId());
        }
        UserEntity receiver = receiverOpt.get();
        receiver.getChatIds().add(chatId);
        userRepository.save(receiver);
    }
}
