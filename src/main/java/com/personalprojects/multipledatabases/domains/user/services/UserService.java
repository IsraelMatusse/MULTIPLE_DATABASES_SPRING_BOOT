package com.personalprojects.multipledatabases.domains.user.services;

import com.personalprojects.multipledatabases.domains.user.model.User;
import com.personalprojects.multipledatabases.domains.user.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }
}
