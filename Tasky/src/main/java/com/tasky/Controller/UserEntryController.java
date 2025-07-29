package com.tasky.Controller;

import com.tasky.Entity.DeleteWorkRequest;
import com.tasky.Entity.User;
import com.tasky.Entity.WorkRequest;
import com.tasky.Service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserEntryController
{
    @Autowired
    private UserEntryService userService;


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

}
