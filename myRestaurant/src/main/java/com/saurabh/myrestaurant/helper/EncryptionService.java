package com.saurabh.myrestaurant.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptionService {
    private final PasswordEncoder passwordEncoder;
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean validate(String password, String encryptedPassword) { //match whether both same string in plaintext
        return passwordEncoder.matches(password, encryptedPassword);
    }
}
