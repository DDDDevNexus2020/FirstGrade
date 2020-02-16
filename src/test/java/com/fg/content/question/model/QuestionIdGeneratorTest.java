package com.fg.content.question.model;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class QuestionIdGeneratorTest {

    /**
     * GIVEN a Question, WHEN the ID is to be generated, THEN a Question ID should be generated with a random UUID.
     */
    @Test
    void generate() {

        // GIVEN a Question
        Question question = new Question("text", 5, null);

        // WHEN the ID is to be generated
        QuestionIdGenerator generator = new QuestionIdGenerator();
        QuestionId questionId = (QuestionId) generator.generate(mock(SharedSessionContractImplementor.class), question);

        // THEN a Question ID should be generated with a random UUID.
        assertNotNull(questionId.getId());
    }
}