package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.Answer;
import pl.ang.backend.repository.AnswerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnswerCheckService {

    @Autowired
    private AnswerRepository answerRepository;

    public Map<String,Object> checkAnswers(Long[] ids){
        Map<String,Object> map = new HashMap<>();
        List<Answer> answers = new ArrayList<>();
        List<Answer> correctAnswers = new ArrayList<>();
        for (Long id : ids) {
            Answer answer = answerRepository.findById(id).orElse(null);
            if(answer != null){
                answers.add(answer);
            }
        }
        List<Long> correctIds = new ArrayList<>();
        List<Long> correctQuestionId = new ArrayList<>();
        if(!answers.isEmpty()){
            correctAnswers = answers.stream().filter(x -> x.getCorrect()).collect(Collectors.toList());
            if(!correctAnswers.isEmpty()){
                correctAnswers.forEach(x -> {
                    correctIds.add(x.getId());
                    correctQuestionId.add(answerRepository.findQuestionIdByAnswerIdNative(x.getId()));
                });
            }
        }
        Long[] correctIdsArray = new Long[correctAnswers.size()];
        Long[] correctQuestionIdsArray = new Long[correctQuestionId.size()];
        map.put("correctAnswerIds", correctIds.toArray(correctIdsArray));
        map.put("correctQuestionIds", correctQuestionId.toArray(correctQuestionIdsArray));
        map.put("score", correctAnswers.size());
        map.put("maxScore", answers.size());
        return map;
    }
}
