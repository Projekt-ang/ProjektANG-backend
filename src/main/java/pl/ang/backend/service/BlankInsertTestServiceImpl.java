package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.BlankInsertTest;
import pl.ang.backend.model.Role;
import pl.ang.backend.repository.BlankInsertTestRepository;
import pl.ang.backend.repository.RoleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BlankInsertTestServiceImpl {

    @Autowired
    private BlankInsertTestRepository blankInsertTestRepository;
    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> save(BlankInsertTest blankInsertTest){
        blankInsertTestRepository.save(blankInsertTest);
        return ResponseEntity.ok(blankInsertTest);
    }

    public void delete(Long id){
        blankInsertTestRepository.deleteById(id);
    }

    public ResponseEntity<?> edit(BlankInsertTest blankInsertTest, Long id){
        Optional<BlankInsertTest> blankInsertTestOptional = blankInsertTestRepository.findById(id);
        if (!blankInsertTestOptional.isPresent())
            return ResponseEntity.notFound().build();
        blankInsertTest.setId(id);
        blankInsertTestRepository.save(blankInsertTest);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> shareBlankInsertTest(Long testId, Long[] roleIds){
        List<Role> roles = new ArrayList<>();
        BlankInsertTest blankInsertTest = blankInsertTestRepository.findById(testId).orElse(null);
        roles = roleRepository.findAllById(Arrays.asList(roleIds));
        if(!roles.isEmpty() && blankInsertTest != null){
            blankInsertTest.setRoles(roles);
        }
        blankInsertTestRepository.save(blankInsertTest);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> getAll(){
        List<BlankInsertTest> readingVideoTests = blankInsertTestRepository.findAll();
        return ResponseEntity.ok(readingVideoTests);
    }

    public ResponseEntity<?> getById(Long id){
        return ResponseEntity.ok(blankInsertTestRepository.findById(id));
    }

}

