package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.ReadingVideoTest;
import pl.ang.backend.repository.ReadingVideoTestRepository;

import java.util.Optional;

@Service
public class ReadingVideoTestServiceImpl {

    @Autowired
    private ReadingVideoTestRepository readingVideoTestRepository;

    public ResponseEntity<?> save(ReadingVideoTest readingVideoTest){
        readingVideoTestRepository.save(readingVideoTest);
        return ResponseEntity.ok(readingVideoTest);
    }

    public void delete(Long id){
        readingVideoTestRepository.deleteById(id);
    }

    public ResponseEntity<?> edit(ReadingVideoTest readingVideoTest, Long id){
        Optional<ReadingVideoTest> readingVideoTestOptional = readingVideoTestRepository.findById(id);
        if (!readingVideoTestOptional.isPresent())
            return ResponseEntity.notFound().build();
        readingVideoTest.setId(id);
        readingVideoTestRepository.save(readingVideoTest);
        return ResponseEntity.noContent().build();
    }

}
