package pl.ang.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ang.backend.service.UserDetailsServiceImpl;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/api/password-generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> passwordGenerateToken(@RequestBody Map<String,Object> body) {
        return userDetailsService.passwordGenerateToken(body.get("email").toString());
    }

    @RequestMapping(value = "/api/password-reset", method = RequestMethod.POST)
    public ResponseEntity<?> passwordReset(@RequestBody Map<String,Object> body) {
        return userDetailsService.passwordReset(body);
    }

    @RequestMapping(value = "/api/password-change", method = RequestMethod.POST)
    public ResponseEntity<?> passwordChange(@RequestBody Map<String,Object> body, @RequestHeader("Authorization") String token) {
        return userDetailsService.passwordChange(body, token);
    }

    @RequestMapping(value = "/api/email-change", method = RequestMethod.POST)
    public ResponseEntity<?> emailChange(@RequestBody Map<String,Object> body, @RequestHeader("Authorization") String token) {
        return userDetailsService.emailChange(body, token);
    }

}
