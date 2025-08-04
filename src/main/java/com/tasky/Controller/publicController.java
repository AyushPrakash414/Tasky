package com.tasky.Controller;

import com.tasky.Entity.User;
import com.tasky.Service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class publicController
{
    @Autowired
    private UserEntryService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> SaveUserInDB(@RequestBody User user)
    {

        return userService.SaveUser(user);
    }

}
