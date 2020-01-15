package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.ReadingVideoTest;
import pl.ang.backend.model.Role;
import pl.ang.backend.repository.ReadingVideoTestRepository;
import pl.ang.backend.repository.RoleRepository;

import java.util.*;

@Service
public class ReadingVideoTestServiceImpl {

    @Autowired
    private ReadingVideoTestRepository readingVideoTestRepository;
    @Autowired
    private RoleRepository roleRepository;

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

    public ResponseEntity<?> shareReadingVideoTest(Long testId, Long[] roleIds){
        List<Role> roles = new ArrayList<>();
        ReadingVideoTest readingVideoTest = readingVideoTestRepository.findById(testId).orElse(null);
        roles = roleRepository.findAllById(Arrays.asList(roleIds));
        if(!roles.isEmpty() && readingVideoTest != null){
            readingVideoTest.setRoles(roles);
        }
        readingVideoTestRepository.save(readingVideoTest);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> getAll(){
        List<ReadingVideoTest> readingVideoTests = readingVideoTestRepository.findAll();
        return ResponseEntity.ok(readingVideoTests);
    }

    public ResponseEntity<?> getById(Long id){
        return ResponseEntity.ok(readingVideoTestRepository.findById(id));
    }

    public ResponseEntity<?> getByName(Map<String, Object> body){
        return ResponseEntity.ok(readingVideoTestRepository.findByName(body.get("name").toString()));
    }

}
