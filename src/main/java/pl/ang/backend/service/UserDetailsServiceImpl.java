package pl.ang.backend.service;

import java.security.SecureRandom;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pl.ang.backend.configuration.JwtTokenUtil;
import pl.ang.backend.model.ConfirmationToken;
import pl.ang.backend.model.Role;
import pl.ang.backend.model.User;
import pl.ang.backend.repository.ConfirmationTokenRepository;
import pl.ang.backend.repository.RoleRepository;
import pl.ang.backend.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptEncoder;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Użytkownik: " + username + " nie istnieje");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    public User save(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("UNCONFIRMED"));
        user.setRoles(roles);
        user.setPassword(bCryptEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public Map<String, String> saveAll(List<User> users, String role) {
        int passwordLength = 10;
        int startChar = 'a';
        int endChar = 'z';
        Map<String, String> loginDetails = new HashMap<>();

        Role group = roleRepository.findByName(role);
        if(group == null){
            group = new Role();
            group.setName(role);
            roleRepository.save(group);
        }

        for(User user : users) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("UNCONFIRMED"));
            roles.add(group);
            user.setRoles(roles);
            String name = (user.getFirstName().charAt(0) + user.getLastName()).toLowerCase();
            int size = userRepository.findByUsernameStartingWith(name).size();
            if(size > 0) {
                name = name + size;
                user.setUsername(name);
            }
            else
                user.setUsername(name);

            SecureRandom random = new SecureRandom();

            String generatedPassword = random.ints(startChar, endChar + 1)
                .limit(passwordLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

            loginDetails.put(name, generatedPassword);

            user.setPassword(bCryptEncoder.encode(generatedPassword));
            userRepository.save(user);
        }

        return loginDetails;
    }

    public ResponseEntity<?> passwordGenerateToken(String email){
        User user = userRepository.findByEmail(email);
        if(user != null){
            if(user.getRoles().contains(roleRepository.findByName("USER"))){
                ConfirmationToken confirmationToken = new ConfirmationToken(user);
                confirmationTokenRepository.save(confirmationToken);
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(user.getEmail());
                mailMessage.setSubject("Reset hasła");
                mailMessage.setFrom("projektang.ug@wp.pl");
                mailMessage.setText("localhost:8080/password-reset/" + confirmationToken.getToken());
                emailSenderService.sendEmail(mailMessage);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> passwordReset(Map<String,Object> body){
        String token = body.get("token").toString();
        String password = body.get("password").toString();
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
        if(confirmationToken != null){
            if(new Date().before(confirmationToken.getExpiryDate())){
                User user = confirmationToken.getUser();
                user.setPassword(bCryptEncoder.encode(password));
                userRepository.save(user);
                return ResponseEntity.ok().build();
            } else {
                confirmationTokenRepository.delete(confirmationToken);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> passwordChange(Map<String,Object> body, String token){
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token.substring(7)));
        String password = body.get("password").toString();
        if(user != null){
            user.setPassword(bCryptEncoder.encode(password));
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> emailChange(Map<String,Object> body, String token){
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token.substring(7)));
        String email = body.get("email").toString();
        if(user != null){
            user.setEmail(email);
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
