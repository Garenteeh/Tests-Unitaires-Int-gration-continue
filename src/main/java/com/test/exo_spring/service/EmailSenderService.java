package com.test.exo_spring.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String text);
}
