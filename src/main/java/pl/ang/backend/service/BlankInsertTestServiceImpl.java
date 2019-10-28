package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.BlankInsertTest;
import pl.ang.backend.repository.BlankInsertTestRepository;

import java.util.Optional;

@Service
public class BlankInsertTestServiceImpl {

    @Autowired
    private BlankInsertTestRepository blankInsertTestRepository;

    public ResponseEntity<?> save(BlankInsertTest blankInsertTest){
        blankInsertTestRepository.save(blankInsertTest);
        return ResponseEntity.ok(blankInsertTest);
    }

    public void delete(Long id){
        blankInsertTestRepository.deleteById(id);
    }

    public ResponseEntity<?> edit(BlankInsertTest blankInsertTest, Long id){
        Optional<BlankInsert> blankInsertTestOptional = blankInsertTestRepository.findById(id);
        if (!blankInsertTestOptional.isPresent())
            return ResponseEntity.notFound().build();
        blankInsertTest.setId(id);
        blankInsertTestRepository.save(blankInsertTest);
        return ResponseEntity.noContent().build();
    }

}
