package com.tasky.Controller;

import com.tasky.Entity.EmailDTO;
import com.tasky.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SendEmail")
public class SendEmailController
{
    @Autowired
    private EmailService service;
    @PostMapping
    public ResponseEntity<?> SendEmailToUser(@RequestBody EmailDTO mail)
    {
        return service.SendEmail(mail);
    }


}
