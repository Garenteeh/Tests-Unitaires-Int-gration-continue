package com.test.exo_spring;

import com.test.exo_spring.objet.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe", "john.doe@gmail.com", LocalDate.of(2000, 1, 1), "Password123");
    }

    @Test
    void testIsValid() {
        assertTrue(user.isValid());
    }

    @Test
    void testIsNotValidEmail() {
        user.setEmail("john.doe");
        assertFalse(user.isValid());
    }

    @Test
    void testIsNotValidAge() {
        user.setBirthDate(LocalDate.of(2020, 1, 1));
        assertFalse(user.isValid());
    }

    @Test
    void testIsNotValidName() {
        user.setFirstName(null);
        user.setLastName(null);
        assertFalse(user.isValid());
    }

    @Test
    void testUserPasswordNotValid() {
        user.setPassword("password");
        assertFalse(user.isValid());
    }
}