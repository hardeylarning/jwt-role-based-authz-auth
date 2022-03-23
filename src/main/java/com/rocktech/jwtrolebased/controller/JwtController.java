package com.rocktech.jwtrolebased.controller;

import com.rocktech.jwtrolebased.entity.JwtRequest;
import com.rocktech.jwtrolebased.entity.JwtResponse;
import com.rocktech.jwtrolebased.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.generateToken(jwtRequest);
    }
}
