package com.tasky.Service;

import com.tasky.Entity.User;
import com.tasky.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntryService
{
    @Autowired
    private userRepository userRepo;
    public ResponseEntity<?> SaveUser(User user)
    {
        try
        {
            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("User is Saved");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong: " + e.getMessage());
        }
    }

}
