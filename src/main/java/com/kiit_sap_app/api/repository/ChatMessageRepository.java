package com.kiit_sap_app.api.repository;


import com.kiit_sap_app.api.entity.ChatMessage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findAllByIdIn(List<ObjectId> chatIds);
    // Additional query methods can be defined here if needed.
}

