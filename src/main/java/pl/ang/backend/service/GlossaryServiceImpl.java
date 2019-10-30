package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.Glossary;
import pl.ang.backend.repository.GlossaryRepository;

import java.util.Optional;

@Service
public class GlossaryServiceImpl {

    @Autowired
    private GlossaryRepository glossaryRepository;

    public ResponseEntity<?> save(Glossary glossary){
        glossaryRepository.save(glossary);
        return ResponseEntity.ok(glossary);
    }

    public void delete(Long id){
        glossaryRepository.deleteById(id);
    }

    public ResponseEntity<?> edit(Glossary glossary, Long id){
        Optional<Glossary> glossaryOptional = glossaryRepository.findById(id);
        if (!glossaryOptional.isPresent())
            return ResponseEntity.notFound().build();
        glossary.setId(id);
        glossaryRepository.save(glossary);
        return ResponseEntity.noContent().build();
    }

}
