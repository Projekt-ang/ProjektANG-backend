package pl.ang.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ang.backend.service.ConfirmationService;

import java.util.Map;

@RestController
public class ConfirmationController {

    @Autowired
    private ConfirmationService confirmationService;

    @RequestMapping(value = "/api/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody Map<String,Object> body, @RequestHeader("Authorization") String token) {
        return confirmationService.generateToken(body.get("email").toString(), token);
    }

    @RequestMapping(value = "/api/verify", method = RequestMethod.POST)
    public ResponseEntity<?> verify(@RequestBody Map<String,Object> body) {
        return confirmationService.verify(body.get("token").toString());
    }

}