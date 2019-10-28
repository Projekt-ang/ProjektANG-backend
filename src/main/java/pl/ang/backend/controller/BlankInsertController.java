package pl.ang.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ang.backend.model.BlankInsertTest;
import pl.ang.backend.repository.BlankInsertTestRepository;
import pl.ang.backend.service.BlankInsertTestServiceImpl;

@RestController
public class BlankInsertController {

    @Autowired
    private BlankInsertTestServiceImpl blankInsertTestService;

    @RequestMapping(value = "/api/saveBlankInsertTest", method = RequestMethod.POST)
    public ResponseEntity<?> saveBlankInsertTest(@RequestBody BlankInsertTest blankInsertTest) {
        return ResponseEntity.ok(blankInsertTestService.save(blankInsertTest));
    }

    @RequestMapping(value = "/api/deleteBlankInsertTest/{id}", method = RequestMethod.DELETE)
    public void deleteBlankInsertTest(@PathVariable long id) {
        blankInsertTestService.delete(id);
    }

    @RequestMapping(value = "/api/editBlankInsertTest/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> editBlankInsertTest(@RequestBody BlankInsertTest blankInsertTest, @PathVariable long id) {
        return ResponseEntity.ok(blankInsertTestService.edit(blankInsertTest, id));
    }

}
