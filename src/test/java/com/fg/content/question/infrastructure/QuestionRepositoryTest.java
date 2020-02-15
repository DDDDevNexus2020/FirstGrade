package com.fg.content.question.infrastructure;

import com.fg.content.question.model.Question;
import com.fg.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class QuestionRepositoryTest extends BaseTest {

    // ----------------------------------------------- MEMBER VARIABLES ------------------------------------------------

    // Class under test
    @Autowired
    private QuestionRepository questionRepository;


    // ------------------------------------------------- TEST METHODS --------------------------------------------------

    /**
     * GIVEN a fully populated Question entity, WHEN the Question is persisted, THEN the Question should be readable
     * from the database AND all values should have been set.
     */
    @Test
    void verifySave() {

        // GIVEN a fully populated Question entity,
        String text = podamFactory.manufacturePojo(String.class);
        int gradeLevel = podamFactory.manufacturePojo(Integer.class);
        List<String> tags = podamFactory.manufacturePojo(ArrayList.class, String.class);
        Question expectedQuestion = new Question(text, gradeLevel, tags);

        // WHEN the Question is persisted,
        Question newQuestion = questionRepository.save(expectedQuestion);
        assertNotNull(newQuestion.getId());

        // THEN the Question should be readable from the database.
        Question actualQuestion = questionRepository
                .getOne(newQuestion.getId()); // Testing our JPA annotations, not repo method
        assertEquals(newQuestion, actualQuestion);

        // AND all values should have been set
        assertEquals(text, actualQuestion.getText());
        assertEquals(gradeLevel, actualQuestion.getGradeLevel());
        assertEquals(tags, actualQuestion.getTags());
    }
}