package com.example.navire.mapper;

import com.example.navire.model.User;
import com.example.navire.dto.LoginDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public LoginDTO toDTO(User user) {
        if (user == null) return null;
        return new LoginDTO(
            user.getId(),
            user.getMail(),
            user.getPassword()
        );
    }

    public User toEntity(LoginDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setMail(dto.getMail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
