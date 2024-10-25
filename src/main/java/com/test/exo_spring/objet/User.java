package com.test.exo_spring.objet;

import java.time.LocalDate;
import java.time.Period;

public class User {

    String firstName;

    String lastName;

    String email;

    LocalDate birthDate;

    String password;

    public User(String firstName, String lastName, String email, LocalDate birthDate,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
    }

    public User() {
    }

    public Boolean isValid() {

        if (firstName == null && lastName == null) {
            return false;
        }

        if (email == null || !email.contains("@") || !email.contains(".") ) {
            return false;
        }

        if (birthDate != null) {
            LocalDate now = LocalDate.now();
            Period period = Period.between(birthDate, now);
            if (period.getYears() < 13) {
                return false;
            }
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        if (!hasUpper || !hasLower || !hasDigit || password.length() < 8 || password.length() > 40) {
            return false;
        }

        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
