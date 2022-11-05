package com.freshvotes.service;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

class UserDetailsServiceTest {

    @Test
    public void generate_encrypted_password() {
        BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);


        System.out.println(encodedPassword);
        assertThat(rawPassword, not(encodedPassword));
    }
}