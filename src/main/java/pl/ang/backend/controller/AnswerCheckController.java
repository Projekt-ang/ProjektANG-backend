package pl.ang.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ang.backend.service.AnswerCheckService;

import java.util.Map;

@RestController
public class AnswerCheckController {

    @Autowired
    private AnswerCheckService answerCheckService;

    @RequestMapping(value = "/api/check-answers", method = RequestMethod.POST)
    public Map<String,Object> checkAnswers(@RequestBody Map<String,Object> body) {
        return answerCheckService.checkAnswers(body);
    }
}
