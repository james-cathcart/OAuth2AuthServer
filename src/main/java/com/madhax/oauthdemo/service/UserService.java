package com.madhax.oauthdemo.service;

import com.madhax.oauthdemo.entity.User;
import com.madhax.oauthdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        log.debug("Getting all users...");
        return (List) this.userRepository.findAll();
    }

    public User getById(Long id) {
        log.debug("Get user by id: {}", id);
        return this.userRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        log.debug("Get user by email: {}", email);
        return this.userRepository.findByEmail(email);
    }

    public User save(User user) {
        log.debug("Saving user...");
        return this.userRepository.save(user);
    }

    public void deleteById(Long id) {
        log.debug("Deleting user with id: {}", id);
        this.userRepository.deleteById(id);
    }

}
