package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.ReadingVideoTest;
import pl.ang.backend.repository.ReadingVideoTestRepository;

@Service
public class ReadingVideoTestServiceImpl {

    @Autowired
    private ReadingVideoTestRepository readingVideoTestRepository;

    public ResponseEntity<?> save(ReadingVideoTest readingVideoTest){
        readingVideoTest.getQuestions().forEach(x -> {
            x.setReadingVideoTest(readingVideoTest);
            x.getAnswers().forEach(y -> y.setQuestion(x));
        });
        return ResponseEntity.ok(readingVideoTestRepository.save(readingVideoTest));
    }

}
