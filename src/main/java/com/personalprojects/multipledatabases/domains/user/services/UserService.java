package com.personalprojects.multipledatabases.domains.user.services;

import com.personalprojects.multipledatabases.domains.user.model.User;
import com.personalprojects.multipledatabases.domains.user.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User create(User user) {
        return userRepo.save(user);
    }

    @Transactional(value = "userTransactionManager")
    public User createUser(User user){
        String password= user.getPassword();
        String hashedPassword=this.encriptPassword(password);
        user.setPassword(hashedPassword);
        return userRepo.save(user);
    }

    @Transactional(value = "userTransactionManager")
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(()->new IllegalArgumentException("User not found with id "+id));
    }
    @Transactional(value = "userTransactionManager")
    public User getUserByName(String name) {
        return userRepo.findByName(name).orElseThrow(()->new IllegalArgumentException("User not found with name "+name));
    }
    public String encriptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
