package com.kiit_sap_app.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "chat_messages")
public class ChatMessage {

    @Id
    private ObjectId id;
    private String senderRollNo;
    private String receiverId;
    private String message;
    private LocalDateTime timestamp;
    private boolean read;
}
