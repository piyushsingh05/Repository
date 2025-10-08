package com.org.newsAggregatorAPI.service;

import com.org.newsAggregatorAPI.DTO.UserDTO;
import com.org.newsAggregatorAPI.entity.Preferences;
import com.org.newsAggregatorAPI.entity.User;
import com.org.newsAggregatorAPI.repository.PreferencesRepository;
import com.org.newsAggregatorAPI.repository.UserRepository;
import com.org.newsAggregatorAPI.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NewsAggregatorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PreferencesRepository _preferencesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(String userName, String password) {
        User user = findByUsername(userName);

        if(!passwordEncoder.matches(password , user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwtUtil.genToken(userName);
    }


    public Preferences retriveNewsPrefrences(String userName) {
        return null;
    }

    public Preferences updateNewsPrefrences(String userName, Preferences newPreferences) {
        return null;
    }

    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
