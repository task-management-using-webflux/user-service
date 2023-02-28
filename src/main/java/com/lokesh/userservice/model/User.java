package com.lokesh.userservice.model;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Getter
@Setter
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String userId;
//            = UUID.randomUUID().toString().substring(0,10);
//    @Size(max = 255)
    private String name;
    @Indexed(unique = true)
    @NotNull
//    @Size(max = 255)
    private String emailId;
}
