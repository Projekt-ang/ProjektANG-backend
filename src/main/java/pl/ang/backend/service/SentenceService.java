package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.Role;
import pl.ang.backend.model.Sentence;
import pl.ang.backend.repository.RoleRepository;
import pl.ang.backend.repository.SentenceRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SentenceService {

    @Autowired
    private SentenceRepository sentenceRepository;
    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> save(Sentence sentence){
        sentenceRepository.save(sentence);
        return ResponseEntity.ok(sentence);
    }

    public void delete(Long id){
        sentenceRepository.deleteById(id);
    }

    public ResponseEntity<?> edit(Sentence sentence, Long id){
        Optional<Sentence> sentenceOptional = sentenceRepository.findById(id);
        if (!sentenceOptional.isPresent())
            return ResponseEntity.notFound().build();
        sentence.setId(id);
        sentenceRepository.save(sentence);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> getAll(){
        List<Sentence> sentences = sentenceRepository.findAll();
        return ResponseEntity.ok(sentences);
    }

    public ResponseEntity<?> getById(Long id){
        return ResponseEntity.ok(sentenceRepository.findById(id));
    }
    
}
