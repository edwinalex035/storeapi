package com.applaudo.store.service;

import com.applaudo.store.domain.model.User;
import com.applaudo.store.domain.repository.UserRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }
}
