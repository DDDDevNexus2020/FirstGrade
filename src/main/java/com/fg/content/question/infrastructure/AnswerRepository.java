package com.fg.content.question.infrastructure;

import com.fg.content.question.model.Answer;
import com.fg.content.question.model.AnswerId;
import com.fg.content.question.model.QuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, AnswerId> {

    /**
     * Return all Answers associated with the given Question (by ID).
     */
    Set<Answer> findByQuestionId(QuestionId questionId);
}
