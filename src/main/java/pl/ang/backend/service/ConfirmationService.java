package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import pl.ang.backend.configuration.JwtTokenUtil;
import pl.ang.backend.model.ConfirmationToken;
import pl.ang.backend.model.Role;
import pl.ang.backend.model.User;
import pl.ang.backend.repository.ConfirmationTokenRepository;
import pl.ang.backend.repository.RoleRepository;
import pl.ang.backend.repository.UserRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class ConfirmationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> generateToken(String email, String token){
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token.substring(7)));
        if(user != null){
            user.setEmail(email);
            userRepository.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Potwierdzenie maila");
            mailMessage.setFrom("projektang.ug@wp.pl");
            mailMessage.setText("localhost:8080/verify/" + confirmationToken.getToken());
            emailSenderService.sendEmail(mailMessage);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> verify(String token){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
        if(confirmationToken != null){
            if(new Date().before(confirmationToken.getExpiryDate())){
                User user = confirmationToken.getUser();
                Set<Role> roles = new HashSet<>();
                roles.add(roleRepository.findByName("USER"));
                user.setRoles(roles);
                userRepository.save(user);
                return ResponseEntity.ok().build();
            } else {
                confirmationTokenRepository.delete(confirmationToken);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
