package com.hatim.application.controllers;

import com.hatim.application.models.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientConfig clientConfig;

    @GetMapping("")
    public ClientConfig getConfig() {
        return clientConfig;
    }
}
