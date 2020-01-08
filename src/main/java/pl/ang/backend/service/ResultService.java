package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.Result;
import pl.ang.backend.model.Role;
import pl.ang.backend.model.User;
import pl.ang.backend.repository.ReadingVideoTestRepository;
import pl.ang.backend.repository.ResultRepository;
import pl.ang.backend.repository.RoleRepository;
import pl.ang.backend.repository.UserRepository;

import java.util.*;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<Result> getResultsForRole(Map<String,Object> body) {
        Integer roleId = (Integer) body.get("roleId");
        Integer readingVideoTestId = (Integer) body.get("readingVideoTestId");
        Integer blankInsertTestId = (Integer) body.get("blankInsertTestId");
        List<Result> results = new ArrayList<>();
        Role role = roleRepository.findById(roleId.longValue()).orElse(null);
        if(role != null){
            Set<User> users = role.getUsers();
            for (User user : users) {
                if(readingVideoTestId != null){
                    results.add(resultRepository.findResultByReadingVideoTestIdAndUserId(user.getId(), readingVideoTestId.longValue()));
                } else {
                    results.add(resultRepository.findResultByBlankInsertTestIdAndUserId(user.getId(), blankInsertTestId.longValue()));
                }
            }
        }
        return results;
    }

    public List<Result> getResultForUser(Long id){
        List<Result> results = new ArrayList<>();
        results.addAll(resultRepository.findResultByUserId(id));
        return results;
    }
}
