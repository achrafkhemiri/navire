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
    public ResponseEntity<?> login(@RequestBody LoginDTO LoginDTO, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        String token = userService.login(LoginDTO.getMail(), LoginDTO.getPassword());
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600); // 1h
        // Active Secure si la requête est HTTPS
        if (request.isSecure()) {
            cookie.setSecure(true);
        }
        response.addCookie(cookie);
        return ResponseEntity.ok().build(); // Ne renvoie pas le token dans le body
    }

    @ExceptionHandler({UserNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception ex) {
        if (ex instanceof UserNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
