package com.tasky.Service;

import com.tasky.Entity.EmailDTO;
import com.tasky.Entity.User;
import com.tasky.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService
{
    @Autowired
    private EmailService service;
    @Autowired
    private EmailDTO email;
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
        email.setBody(work+ " is the New Work is Added to Your Stack 🔥🐦‍🔥🪄");
        email.setFrom("prakashayush414@gmail.com");
        email.setTo(userInDB.get().getEmail());
        email.setSubject("New Work is Added to you Stack!!");
        if (userInDB.isPresent()) {
            User foundUser = userInDB.get();

            // 💡 Initialize the list if it's null
            if (foundUser.getRemainingWork() == null) {
                foundUser.setRemainingWork(new ArrayList<>());
            }

            foundUser.getRemainingWork().add(work);
            service.SendEmail(email);
            return SaveUser(foundUser);  // Assuming SaveUser saves and returns ResponseEntity
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)  // Better than 500
                    .body("User not found.");
        }
    }

    public ResponseEntity<?> removeWork(String userName,String work)
    {
        if (work==null||userName==null) return ResponseEntity.badRequest().body("Username or work cannot be null.");
        Optional<User> userInDB = userRepo.findByuserName(userName);
        if (userInDB.isPresent())
        {
            List<String> worksOfUser =userInDB.get().getRemainingWork();
            boolean exists = worksOfUser.contains(work);
            if (exists)
            {
                worksOfUser.remove(work);
                userInDB.get().setRemainingWork(worksOfUser);
                userRepo.save(userInDB.get());
                return ResponseEntity.ok("Work removed successfully.");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("work not found.");
            }

        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }



}
