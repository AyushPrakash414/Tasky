package com.tasky.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingPong {
    @GetMapping
    public ResponseEntity<?> pingPong()
    {
         return ResponseEntity.status(200).build();
    }

}
