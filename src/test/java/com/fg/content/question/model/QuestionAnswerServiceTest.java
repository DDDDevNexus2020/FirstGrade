package com.fg.content.question.model;

import com.fg.content.question.infrastructure.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuestionAnswerServiceTest {

    // ------------------------------------------------- DEPENDENCIES --------------------------------------------------

    @Mock
    private AnswerRepository answerRepositoryMock;


    // ----------------------------------------------- MEMBER VARIABLES ------------------------------------------------

    // Class under test
    @InjectMocks
    private QuestionAnswerService questionAnswerService;


    // ------------------------------------------------- TEST METHODS --------------------------------------------------

    /**
     * GIVEN the ID of a Question AND an Answer with text that is a duplicate among all Answers already associated with
     * the Question, WHEN an attempt is made to add the Answer to the Question, THEN an error should be thrown.
     */
    @Test
    void addAnswerToQuestion_duplicate() {

        // GIVEN the ID of a Question
        QuestionId questionId = new QuestionId(UUID.randomUUID());

        // AND an Answer with text that is a duplicate among all Answers already associated with the Question
        Set<Answer> existingAnswers = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            existingAnswers.add(new Answer("Answer " + i, false));
        }
        doReturn(existingAnswers).when(answerRepositoryMock).findByQuestionId(questionId);
        Answer newAnswer = new Answer("Answer 4", true);

        // WHEN an attempt is made to add the Answer to the Question
        // THEN an error should be thrown.
        assertThrows(IllegalArgumentException.class,
                     () -> questionAnswerService.addAnswerToQuestion(questionId, newAnswer));

        // Verify mocks
        verify(answerRepositoryMock).findByQuestionId(questionId);
    }

    /**
     * GIVEN the ID of a Question AND an Answer with text that is not duplicated among any Answers already associated
     * with the Question, WHEN an attempt is made to add the Answer to the Question, THEN the Answer should be
     * associated with Question.
     */
    @Test
    void addAnswerToQuestion_notDuplicate() {

        // GIVEN the ID of a Question
        QuestionId questionId = new QuestionId(UUID.randomUUID());

        // AND an Answer with text that is not duplicated among any Answers already associated with the Question
        Set<Answer> existingAnswers = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            existingAnswers.add(new Answer("Answer " + i, false));
        }
        doReturn(existingAnswers).when(answerRepositoryMock).findByQuestionId(questionId);
        Answer newAnswer = new Answer("New Text", false);

        // WHEN the Answer is checked to see if it is a duplicate
        questionAnswerService.addAnswerToQuestion(questionId, newAnswer);

        // THEN the Answer should be associated with Question.
        assertEquals(questionId, newAnswer.getQuestionId());

        // Verify Mocks
        verify(answerRepositoryMock).findByQuestionId(questionId);
    }

    /**
     * GIVEN the ID of a Question AND the Question has an existing Answer flagged as correct AND a new correct Answer is
     * given, WHEN the Answer is set as the new correct answer, THEN the previous correct Answer be returned AND should
     * no longer be the correct Answer.
     */
    @Test
    void setCorrectAnswer() {

        // GIVEN the ID of a Question
        QuestionId questionId = new QuestionId(UUID.randomUUID());

        // AND the Question has an existing Answer flagged as correct
        Set<Answer> existingAnswers = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            existingAnswers.add(new Answer("Answer " + i, i == 3));
        }
        doReturn(existingAnswers).when(answerRepositoryMock).findByQuestionId(questionId);

        // AND a new correct Answer is given
        Answer newCorrectAnswer = new Answer("Correct Answer", true);

        // WHEN the Answer is set as the new correct answer
        Answer previousCorrectAnswer = questionAnswerService.setCorrectAnswer(questionId, newCorrectAnswer);

        // THEN the previous correct Answer be returned
        assertEquals("Answer 3", previousCorrectAnswer.getText());

        // AND should no longer be the correct Answer.
        assertFalse(previousCorrectAnswer.isCorrect());

        // Verify Mocks
        verify(answerRepositoryMock).findByQuestionId(questionId);
    }
}