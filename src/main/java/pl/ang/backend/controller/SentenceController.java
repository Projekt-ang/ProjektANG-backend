package pl.ang.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ang.backend.model.Sentence;
import pl.ang.backend.service.SentenceService;

@RestController
public class SentenceController {

    @Autowired
    private SentenceService sentenceService;

    @RequestMapping(value = "/api/sentence", method = RequestMethod.POST)
    public ResponseEntity<?> saveSentence(@RequestBody Sentence sentence) {
        return ResponseEntity.ok(sentenceService.save(sentence));
    }

    @RequestMapping(value = "/api/sentence/{id}", method = RequestMethod.DELETE)
    public void deleteSentence(@PathVariable long id) {
        sentenceService.delete(id);
    }

    @RequestMapping(value = "/api/sentence/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> editSentence(@RequestBody Sentence sentence, @PathVariable long id) {
        return ResponseEntity.ok(sentenceService.edit(sentence, id));
    }

    @RequestMapping(value = "/api/sentences", method = RequestMethod.GET)
    public ResponseEntity<?> getSentences() {
        return ResponseEntity.ok(sentenceService.getAll());
    }

    @RequestMapping(value = "/api/sentence/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSentenceById(@PathVariable long id) {
        return ResponseEntity.ok(sentenceService.getById(id));
    }
    
}
