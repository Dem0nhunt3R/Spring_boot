package com.example.spring_boot.services;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailSender;

    public void send(String mail, int id) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        mimeMessage.setFrom(new InternetAddress("javamailsenderlesson4@gmail.com"));
        helper.setTo(mail);
        helper.setText("click <a href='http://localhost:8080/user/activate/" + id + "'>click here</a> to activate", true);
        javaMailSender.send(mimeMessage);
    }
}
