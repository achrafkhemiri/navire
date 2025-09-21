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

    @GetMapping("/projets")
    public ResponseEntity<?> getAllUserProjects() {
        // TODO: Replace with actual logic to fetch all user projects
        return ResponseEntity.ok().body("All user projects endpoint stub");
    }

    @GetMapping("/{id}/projets")
    public ResponseEntity<?> getUserProjects(@PathVariable Long id) {
        // TODO: Replace with actual service logic to fetch user's projects
        // Example: List<ProjetDTO> projets = userService.getUserProjects(id);
        return ResponseEntity.ok().body("User projects endpoint stub");
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginDTO> register(@RequestBody @jakarta.validation.Valid LoginDTO LoginDTO) {
        LoginDTO created = userService.register(LoginDTO.getMail(), LoginDTO.getPassword());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @jakarta.validation.Valid LoginDTO LoginDTO, jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) {
        String token = userService.login(LoginDTO.getMail(), LoginDTO.getPassword());
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("jwt", token);
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
