package com.tasky.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class healthCheck
{
    @GetMapping
    public String  getHealth()
    {
        return ("Okk Jai Shree Ram");
    }

}
