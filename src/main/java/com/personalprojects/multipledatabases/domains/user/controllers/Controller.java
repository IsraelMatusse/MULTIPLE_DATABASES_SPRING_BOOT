package com.personalprojects.multipledatabases.domains.user.controllers;

import com.personalprojects.multipledatabases.domains.user.model.User;
import com.personalprojects.multipledatabases.domains.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class Controller {
private final UserService userService;

@PostMapping
    public ResponseEntity<User>createUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
