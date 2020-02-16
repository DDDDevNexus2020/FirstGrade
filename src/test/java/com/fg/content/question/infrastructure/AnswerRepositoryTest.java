package com.fg.content.question.infrastructure;

import com.fg.content.question.model.Answer;
import com.fg.content.question.model.QuestionId;
import com.fg.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AnswerRepositoryTest extends BaseTest {

    // ----------------------------------------------- MEMBER VARIABLES ------------------------------------------------

    // Class under test
    @Autowired
    private AnswerRepository answerRepository;


    // ------------------------------------------------- TEST METHODS --------------------------------------------------

    /**
     * GIVEN a fully populated Answer entity, WHEN the Answer is persisted, THEN the Answer should be readable from the
     * database.
     */
    @Test
    void verifySave() {

        // GIVEN a fully populated Answer entity,
        String text = podamFactory.manufacturePojo(String.class);
        Answer expectedAnswer = new Answer(text, false);

        // WHEN the Answer is persisted,
        Answer newAnswer = answerRepository.save(expectedAnswer);
        assertNotNull(newAnswer.getId());

        // THEN the Answer should be readable from the database.
        Answer actualAnswer = answerRepository
                .getOne(newAnswer.getId()); // Testing our JPA annotations, not repo method
        assertEquals(newAnswer, actualAnswer);
    }

    /**
     * GIVEN a Question ID AND a number of Answers are persisted as associated with a that Question, WHEN a list of all
     * Answers associated with the Question is requested, THEN a list of Answers should be returned AND they should be
     * the Answers associated with that Question.
     */
    @Test
    void findByQuestionId() {

        // GIVEN a Question ID
        QuestionId questionId = new QuestionId(UUID.randomUUID());

        // AND a number of Answers are persisted as associated with a that Question
        for (int i = 0; i < 5; i++) {
            Answer answer = new Answer("Answer " + i, i == 3);
            answer.addToQuestion(questionId);
            answerRepository.save(answer);
        }

        // WHEN a list of all Answers associated with the Question is requested
        Set<Answer> answers = answerRepository.findByQuestionId(questionId);

        // THEN a list of Answers should be returned
        assertFalse(answers.isEmpty());

        // AND they should be the Answers associated with that Question.
        assertTrue(answers.stream().allMatch(a -> a.getQuestionId().equals(questionId)));
    }
}