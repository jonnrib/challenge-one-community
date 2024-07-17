package com.community.forumHub.controller;

import com.community.forumHub.infra.exception.security.JWTTokenData;
import com.community.forumHub.infra.exception.security.JWTTokenService;
import com.community.forumHub.domain.User;
import com.community.forumHub.dto.AuthenticationDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTTokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto data) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            Authentication authentication = manager.authenticate(authenticationToken);

            var user = (User) authentication.getPrincipal();
            var token = tokenService.generateToken(user);

            return ResponseEntity.ok(new JWTTokenData(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid user or expired token!");
        }
    }
}
