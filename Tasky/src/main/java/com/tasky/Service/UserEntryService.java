package com.tasky.Service;

import com.tasky.Entity.User;
import com.tasky.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

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

    public ResponseEntity<?> addWork(String work, String user) {
        Optional<User> userInDB = userRepo.findByuserName(user);

        if (userInDB.isPresent()) {
            User foundUser = userInDB.get();

            // 💡 Initialize the list if it's null
            if (foundUser.getRemainingWork() == null) {
                foundUser.setRemainingWork(new ArrayList<>());
            }

            foundUser.getRemainingWork().add(work);
            return SaveUser(foundUser);  // Assuming SaveUser saves and returns ResponseEntity
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)  // Better than 500
                    .body("User not found.");
        }
    }


}
