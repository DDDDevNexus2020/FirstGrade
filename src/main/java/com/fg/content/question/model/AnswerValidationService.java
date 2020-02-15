package com.fg.content.question.model;

import com.fg.content.question.infrastructure.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerValidationService {

    // ------------------------------------------------- DEPENDENCIES --------------------------------------------------

    private final AnswerRepository answerRepository;


    // ------------------------------------------------ PUBLIC METHODS -------------------------------------------------

    /**
     * Adds an Answer to the given Question.
     */
    public void addAnswerToQuestion(Answer answer, UUID questionId) {

        // Answers cannot be duplicated
        if (this.isDuplicateAnswer(questionId, answer)) {
            throw new IllegalArgumentException("Answers with duplicate text are not allowed.");
        }

        // All is good.
        answer.addToQuestion(questionId);
    }


    // ------------------------------------------------ PRIVATE METHODS ------------------------------------------------

    /**
     * Returns whether or not the given Question already has an Answer equal to the text of the given Answer.
     */
    protected boolean isDuplicateAnswer(UUID questionId, Answer answer) {

        // Get all Answers already associated with the Question
        Set<Answer> answers = answerRepository.findByQuestionId(questionId);

        // Find a duplicate
        return answers.stream().anyMatch(a -> StringUtils.equals(a.getText(), answer.getText()));
    }
}
