package org.itsnick78.win_win_travel_tt.services;

import org.itsnick78.win_win_travel_tt.models.User;
import org.itsnick78.win_win_travel_tt.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void registerUser(User createdUser) {
        if (userRepository.existsByEmail(createdUser.getEmail())) {
            throw new RuntimeException("Email address already in use");
        }
        User user = new User();
        user.setEmail(createdUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(createdUser.getPassword()));
        userRepository.save(user);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
    }
}
