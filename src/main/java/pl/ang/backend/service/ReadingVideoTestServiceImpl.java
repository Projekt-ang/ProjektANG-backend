package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.ReadingVideoTest;
import pl.ang.backend.model.Role;
import pl.ang.backend.model.Tag;
import pl.ang.backend.model.User;
import pl.ang.backend.repository.ReadingVideoTestRepository;
import pl.ang.backend.repository.RoleRepository;
import pl.ang.backend.repository.TagRepository;
import pl.ang.backend.repository.UserRepository;

import java.util.*;

@Service
public class ReadingVideoTestServiceImpl {

    @Autowired
    private ReadingVideoTestRepository readingVideoTestRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> save(ReadingVideoTest readingVideoTest){
        List<Tag> tags = tagRepository.findAll();
        if(!readingVideoTest.getTags().isEmpty()){
            List<Tag> tagsToDelete = new ArrayList<>();
            List<Tag> tagsToAdd = new ArrayList<>();
            for (Tag tag : tags) {
                for (Tag tag1 : readingVideoTest.getTags()) {
                    if(tag.getText().toLowerCase().equals(tag1.getText().toLowerCase())){
                        tagsToDelete.add(tag1);
                        tagsToAdd.add(tag);
                    }
                }
            }
            readingVideoTest.getTags().removeAll(tagsToDelete);
            readingVideoTest.getTags().addAll(tagsToAdd);
        }
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

    public ResponseEntity<?> getByUserId(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            return ResponseEntity.ok(readingVideoTestRepository.findByRoles(user.getRoles()));
        }
        else
            return ResponseEntity.ok(null);
    }

}
