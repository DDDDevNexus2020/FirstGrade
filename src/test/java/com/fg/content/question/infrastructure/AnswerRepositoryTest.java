package com.fg.content.question.infrastructure;

import com.fg.content.question.model.Answer;
import com.fg.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}