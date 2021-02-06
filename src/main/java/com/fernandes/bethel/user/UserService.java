package com.fernandes.bethel.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String addUser(UserRegistrationRequest userRegistrationRequest){
        User user = new User(
                userRegistrationRequest.getFirstName(),
                userRegistrationRequest.getLastName(),
                userRegistrationRequest.getUsername(),
                passwordEncoder.encode(userRegistrationRequest.getPassword()),
                userRegistrationRequest.getRoles(),
                userRegistrationRequest.getUserSocietyName()
        );

        userRepository.save(user);
        return "User has been Registered Successfully";
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
}
