package pl.ang.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ang.backend.model.Answer;
import pl.ang.backend.model.ReadingVideoTest;
import pl.ang.backend.model.Result;
import pl.ang.backend.model.User;
import pl.ang.backend.repository.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnswerCheckService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ReadingVideoTestRepository readingVideoTestRepository;
    @Autowired
    private BlankInsertTestRepository blankInsertTestRepository;
    @Autowired
    private UserRepository userRepository;

    public Map<String,Object> checkAnswers(Map<String,Object> body){
        List<Integer> ids = (ArrayList) body.get("answerIds");
        Integer userid = (Integer) body.get("userId");
        Integer testId = (Integer) body.get("testId");
        Integer blankTestId = (Integer) body.get("blankTestId");
        Map<String,Object> map = new HashMap<>();
        List<Answer> answers = new ArrayList<>();
        List<Answer> correctAnswers = new ArrayList<>();
        for (Integer id : ids) {
            Answer answer = answerRepository.findById(id.longValue()).orElse(null);
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
                    Long questionId = answerRepository.findQuestionIdByAnswerIdNative(x.getId());
                    if(questionId != null){
                        correctQuestionId.add(questionId);

                    }
                    Long blankId = answerRepository.findBlankByAnswerIdNative(x.getId());
                    if(blankId != null){
                        correctQuestionId.add(blankId);

                    }
                });
            }
        }
        Long[] correctIdsArray = new Long[correctAnswers.size()];
        Long[] correctQuestionIdsArray = new Long[correctQuestionId.size()];
        map.put("correctAnswerIds", correctIds.toArray(correctIdsArray));
        map.put("correctQuestionIds", correctQuestionId.toArray(correctQuestionIdsArray));
        map.put("score", correctAnswers.size());
        map.put("maxScore", answers.size());

        Result result = new Result();
        Integer points = correctQuestionId.size();
        Integer maxPoints = answers.size();
        float divide = (points / (float) maxPoints)*100;
        int percentage = Math.round(divide);
        result.setPoints(points.longValue());
        result.setMaxPoints(maxPoints.longValue());
        result.setPercentage(percentage);
        result.setUser(userRepository.findById(userid.longValue()).orElse(null));
        if(testId != null || testId.intValue() != 0){
            result.setReadingVideoTest(readingVideoTestRepository.findById(testId.longValue()).orElse(null));
        } else if(blankTestId != null || blankTestId.intValue() != 0) {
            result.setBlankInsertTest(blankInsertTestRepository.findById(blankTestId.longValue()).orElse(null));
        }
        resultRepository.save(result);
        return map;
    }
}
