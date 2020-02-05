package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.BlankInsertTest;
import pl.ang.backend.model.Role;
import pl.ang.backend.model.Tag;
import pl.ang.backend.repository.BlankInsertTestRepository;
import pl.ang.backend.repository.RoleRepository;
import pl.ang.backend.repository.TagRepository;

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
    @Autowired
    private TagRepository tagRepository;

    public ResponseEntity<?> save(BlankInsertTest blankInsertTest){
        List<Tag> tags = tagRepository.findAll();
        if(!blankInsertTest.getTags().isEmpty()){
            List<Tag> tagsToDelete = new ArrayList<>();
            List<Tag> tagsToAdd = new ArrayList<>();
            for (Tag tag : tags) {
                for (Tag tag1 : blankInsertTest.getTags()) {
                    if(tag.getText().toLowerCase().equals(tag1.getText().toLowerCase())){
                        tagsToDelete.add(tag1);
                        tagsToAdd.add(tag);
                    }
                }
            }
            blankInsertTest.getTags().removeAll(tagsToDelete);
            blankInsertTest.getTags().addAll(tagsToAdd);
        }
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

