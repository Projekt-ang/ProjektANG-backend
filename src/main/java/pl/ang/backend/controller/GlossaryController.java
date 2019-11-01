package pl.ang.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ang.backend.model.Glossary;
import pl.ang.backend.service.GlossaryServiceImpl;

@RestController
public class GlossaryController {

    @Autowired
    private GlossaryServiceImpl glossaryService;

    @RequestMapping(value = "/api/glossary", method = RequestMethod.POST)
    public ResponseEntity<?> saveGlossary(@RequestBody Glossary glossary) {
        return ResponseEntity.ok(glossaryService.save(glossary));
    }

    @RequestMapping(value = "/api/glossary/{id}", method = RequestMethod.DELETE)
    public void deleteGlossary(@PathVariable long id) {
        glossaryService.delete(id);
    }

    @RequestMapping(value = "/api/glossary/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> editGlossary(@RequestBody Glossary glossary, @PathVariable long id) {
        return ResponseEntity.ok(glossaryService.edit(glossary, id));
    }

}
