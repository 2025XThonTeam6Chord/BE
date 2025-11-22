package com.example.naega.service.answer;

import com.example.naega.common.GlobalHeaderInterceptor;
import com.example.naega.dto.answer.UserAnswerReq;
import com.example.naega.dto.answer.UserAnswerRes;
import com.example.naega.entity.Answers;
import com.example.naega.entity.Question;
import com.example.naega.entity.Users;
import com.example.naega.repository.answer.AnswerRepository;
import com.example.naega.repository.answer.QuestionRepository;
import com.example.naega.repository.answer.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answersRepository;
    private final QuestionRepository questionRepository;
    private final UsersRepository usersRepository;

    public void saveAnswer(UserAnswerReq userAnswerReq) {
        Long questionId = userAnswerReq.questionId();
        Long userId = userAnswerReq.userId();
        String result = userAnswerReq.answer();
        Long count = answersRepository.countByUsersId(userId);

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 질문이 존재하지 않습니다. id=" + questionId));
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 질문이 존재하지 않습니다. id=" + userId));

        Answers answers = Answers.builder()
                .users(users)
                .question(question)
                .result(result)
                .count(count+1)
                .build();

        answersRepository.save(answers);
    }

    public UserAnswerRes getQuestion() {
        Question question = questionRepository.findRandomQuestion()
                .orElseThrow(() -> new IllegalArgumentException(("질문이 한개도 존재하지 않습니다.")));
        String userId = GlobalHeaderInterceptor.localThread.get();
        Long count = answersRepository.countByUsersId(Long.parseLong(userId));


        return UserAnswerRes.builder()
                .questionId(question.getId())
                .responseType(question.getResponseType())
                .content(question.getContents())
                .question1(question.getQuestion1())
                .question2(question.getQuestion2())
                .question3(question.getQuestion3())
                .question4(question.getQuestion4())
                .question5(question.getQuestion5())
                .count(count)
                .build();
    }
}
