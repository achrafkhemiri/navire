package com.example.navire.controller;

import com.example.navire.dto.LoginDTO;
import com.example.navire.services.UserService;
import com.example.navire.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<LoginDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginDTO> register(@RequestBody LoginDTO LoginDTO) {
        LoginDTO created = userService.register(LoginDTO.getMail(), LoginDTO.getPassword());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO LoginDTO) {
        String token = userService.login(LoginDTO.getMail(), LoginDTO.getPassword());
        return ResponseEntity.ok(token);
    }

    @ExceptionHandler({UserNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception ex) {
        if (ex instanceof UserNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
