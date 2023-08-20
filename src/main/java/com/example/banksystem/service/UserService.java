package com.example.banksystem.service;

import com.example.banksystem.entity.User;
import com.example.banksystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    private BCryptPasswordEncoder encoder()  {
        return new BCryptPasswordEncoder();
    }

    public void saveUser(User user) {
        user.setPassword(encoder().encode(user.getPassword()));


        userRepository.save(user);
    }

}
