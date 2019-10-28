package pl.ang.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ang.backend.model.ReadingVideoTest;
import pl.ang.backend.service.ReadingVideoTestServiceImpl;

@RestController
public class ReadingVideoController {

    @Autowired
    private ReadingVideoTestServiceImpl readingVideoTestService;

    @RequestMapping(value = "/api/readingVideoTest", method = RequestMethod.POST)
    public ResponseEntity<?> saveReadingVideoTest(@RequestBody ReadingVideoTest readingVideoTest) {
        return ResponseEntity.ok(readingVideoTestService.save(readingVideoTest));
    }

    @RequestMapping(value = "/api/readingVideoTest/{id}", method = RequestMethod.DELETE)
    public void deleteReadingVideoTest(@PathVariable long id) {
        readingVideoTestService.delete(id);
    }

    @RequestMapping(value = "/api/readingVideoTest/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> editReadingVideoTest(@RequestBody ReadingVideoTest readingVideoTest, @PathVariable long id) {
        return ResponseEntity.ok(readingVideoTestService.edit(readingVideoTest, id));
    }

}
