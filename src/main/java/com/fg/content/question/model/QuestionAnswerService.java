package com.fg.content.question.model;

import com.fg.content.question.infrastructure.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionAnswerService {

    // ------------------------------------------------- DEPENDENCIES --------------------------------------------------

    private final AnswerRepository answerRepository;


    // ------------------------------------------------ PUBLIC METHODS -------------------------------------------------

    /**
     * Adds an Answer to the given Question.
     */
    public void addAnswerToQuestion(QuestionId questionId, Answer answer) {

        // Answers cannot be duplicated
        if (this.isDuplicateAnswer(questionId, answer)) {
            throw new IllegalArgumentException("Answers with duplicate text are not allowed.");
        }

        // Answer now references Question
        answer.addToQuestion(questionId);
    }

    /**
     * Makes the given Answer the correct answer to the given question. This method will cause the current correct
     * Answer to no longer be the correct Answer. The previous correct answer is returned.
     */
    public Answer setCorrectAnswer(QuestionId questionId, Answer newCorrectAnswer) {

        // Find the existing correct answer
        Answer previousCorrectAnswer = this.getCorrectAnswerForQuestion(questionId);

        // It is no longer the correct answer
        if (previousCorrectAnswer != null) {
            previousCorrectAnswer.setCorrect(false);
        }

        // The new one is
        newCorrectAnswer.setCorrect(true);

        // Return the previous
        return previousCorrectAnswer;
    }


    // ------------------------------------------------ PRIVATE METHODS ------------------------------------------------

    /**
     * Returns whether or not the given Question already has an Answer equal to the text of the given Answer.
     */
    private boolean isDuplicateAnswer(QuestionId questionId, Answer answer) {

        // Get all Answers already associated with the Question
        Set<Answer> answers = answerRepository.findByQuestionId(questionId);

        // Find a duplicate
        return answers.stream().anyMatch(a -> StringUtils.equals(a.getText(), answer.getText()));
    }

    /**
     * Returns the current correct Answer for the given question. If a correct answer has not been set, then null is
     * returned.
     */
    private Answer getCorrectAnswerForQuestion(QuestionId questionId) {

        // Get all Answers already associated with the Question
        Set<Answer> answers = answerRepository.findByQuestionId(questionId);

        // Return the correct answer
        return answers.stream().filter(Answer::isCorrect).findFirst().orElse(null);
    }
}
