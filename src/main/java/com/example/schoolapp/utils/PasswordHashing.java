package com.example.schoolapp.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {
        public static String hashPassword(String plainTextPassword) {
            return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
        }
}
