package pl.ang.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ang.backend.service.EmailSenderService;

@RestController
public class TestController {

    @Autowired
    private EmailSenderService emailSenderService;

    @RequestMapping({ "/test" })
    @PreAuthorize("hasRole('USER')")
    public String test() {
        return "hello world";
    }

    @RequestMapping({ "/admin/test" })
    @PreAuthorize("hasRole('ADMIN')")
    public String adminTest() {
        return "hello world admin";
    }

    @RequestMapping({ "/mail" })
    public String emailTest() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("kjmasa2@gmail.com");
        mailMessage.setSubject("TEST MAILA");
        mailMessage.setFrom("mail.projektang@gmail.com");
        mailMessage.setText("Testowy mail z kontrolera");

        emailSenderService.sendEmail(mailMessage);

        return "mail";
    }

}
