package com.tasky.Controller;

import com.tasky.Entity.DeleteWorkRequest;
import com.tasky.Entity.LoginRequest;
import com.tasky.Entity.User;
import com.tasky.Entity.WorkRequest;
import com.tasky.Service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserEntryController
{
    @Autowired
    private UserEntryService userService;

    @PostMapping ("/signing")
    public ResponseEntity<?> Signing (@RequestBody User user)
    {
        return userService.SaveUser(user);
    }
    @PostMapping ("/AddTask")
    public ResponseEntity<?> AddTask(@RequestBody WorkRequest user)
    {
        return userService.addWork(user.getWork(),user.getUserName());
    }

    @PostMapping("/deleteWork")
    public ResponseEntity<?> DeleteWork(@RequestBody WorkRequest request)
    {
        return userService.removeWork(request.getUserName(), request.getWork());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user)
    {
        return userService.LoginService(user);
    }

}
