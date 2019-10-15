package pl.ang.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.ang.backend.model.ReadingVideoTest;
import pl.ang.backend.repository.ReadingVideoTestRepository;
import pl.ang.backend.service.ReadingVideoTestServiceImpl;

@RestController
public class ReadingVideoController {

    @Autowired
    private ReadingVideoTestServiceImpl readingVideoTestService;

    @RequestMapping(value = "/api/saveReadingVideoTest", method = RequestMethod.POST)
    public ResponseEntity<?> saveReadingVideoTest(@RequestBody ReadingVideoTest readingVideoTest) {
        return ResponseEntity.ok(readingVideoTestService.save(readingVideoTest));
    }

}
