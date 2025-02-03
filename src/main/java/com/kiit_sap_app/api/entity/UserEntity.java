package com.kiit_sap_app.api.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username; // Roll number
    @NonNull
    private String password;
    private List<String> roles = new ArrayList<>();
    private List<ObjectId> chatIds=new ArrayList<>();
}

