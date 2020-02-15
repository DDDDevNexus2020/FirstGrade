package com.fg.content.question.infrastructure;

import com.fg.content.question.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    /**
     * Return all Answers associated with the given Question (by ID).
     */
    Set<Answer> findByQuestionId(UUID questionId);
}
