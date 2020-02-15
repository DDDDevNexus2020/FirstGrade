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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AnswerValidationServiceTest {

    // ------------------------------------------------- DEPENDENCIES --------------------------------------------------

    @Mock
    private AnswerRepository answerRepositoryMock;


    // ----------------------------------------------- MEMBER VARIABLES ------------------------------------------------

    // Class under test
    @InjectMocks
    private AnswerValidationService answerValidationService;


    // ------------------------------------------------- TEST METHODS --------------------------------------------------

    /**
     * GIVEN the ID of a Question AND an Answer with text that is duplicated among all Answers already associated with
     * the Question, WHEN the Answer is checked to see if it is a duplicate, THEN the existing Answers for the Question
     * should be retrieved AND true should be returned.
     */
    @Test
    void isDuplicateAnswer_duplicate() {

        // GIVEN the ID of a Question
        UUID questionId = UUID.randomUUID();

        // AND an Answer with text that is duplicated among all Answers already associated with the Question
        Set<Answer> existingAnswers = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            existingAnswers.add(new Answer(questionId, "Answer " + i));
        }
        doReturn(existingAnswers).when(answerRepositoryMock).findByQuestionId(questionId);
        Answer newAnswer = new Answer(questionId, "Answer 4");

        // WHEN the Answer is checked to see if it is a duplicate
        boolean isDuplicateAnswer = answerValidationService.isDuplicateAnswer(questionId, newAnswer);

        // THEN the existing Answers for the Question should be retrieved
        verify(answerRepositoryMock).findByQuestionId(questionId);

        // AND true should be returned.
        assertTrue(isDuplicateAnswer);
    }

    /**
     * GIVEN the ID of a Question AND an Answer with text that is not duplicated among all Answers already associated
     * with the Question, WHEN the Answer is checked to see if it is a duplicate, THEN the existing Answers for the
     * Question should be retrieved AND false should be returned.
     */
    @Test
    void isDuplicateAnswer_notDuplicate() {

        // GIVEN the ID of a Question
        UUID questionId = UUID.randomUUID();

        // AND an Answer with text that is not duplicated among all Answers already associated with the Question
        Set<Answer> existingAnswers = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            existingAnswers.add(new Answer(questionId, "Answer " + i));
        }
        doReturn(existingAnswers).when(answerRepositoryMock).findByQuestionId(questionId);
        Answer newAnswer = new Answer(questionId, "New Text");

        // WHEN the Answer is checked to see if it is a duplicate
        boolean isDuplicateAnswer = answerValidationService.isDuplicateAnswer(questionId, newAnswer);

        // THEN the existing Answers for the Question should be retrieved
        verify(answerRepositoryMock).findByQuestionId(questionId);

        // AND false should be returned.
        assertFalse(isDuplicateAnswer);
    }
}