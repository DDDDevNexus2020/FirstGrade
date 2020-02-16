package com.fg.content.question.model;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class AnswerIdGeneratorTest {

    /**
     * GIVEN an Answer, WHEN the ID is to be generated, THEN an Answer ID should be generated with a random UUID.
     */
    @Test
    void generate() {

        // GIVEN an Answer
        Answer answer = new Answer("text", false);

        // WHEN the ID is to be generated
        AnswerIdGenerator generator = new AnswerIdGenerator();
        AnswerId answerId = (AnswerId) generator.generate(mock(SharedSessionContractImplementor.class), answer);

        // THEN an Answer ID should be generated with a random UUID.
        assertNotNull(answerId.getId());
    }
}