package com.tasky.Entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@Document(collection ="TaskyUsers")
public class User
{
    @Indexed(unique = true)
    private String UserName;
    private String Password;
    private String Email;
    private List<String> RemainingWork;
    private List<String>Roles;
    private ObjectId id;
    private String Quotes;
}
