package kz.org.iqro.iqro.services;

import kz.org.iqro.iqro.entities.User;
import kz.org.iqro.iqro.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }
}
