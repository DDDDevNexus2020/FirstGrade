package com.fg.content.question.model;

import com.fg.infrastructure.model.ValidatingModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Answer implements ValidatingModel<Answer> {

    // --------------------------------------------------- PROPERTIES --------------------------------------------------

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message = "The answer text is required.")
    private String text;

    @NotNull(message = "The answer must be associated with a question.")
    private UUID questionId;

    private boolean isCorrect;


    // ------------------------------------------------- CONSTRUCTOR ---------------------------------------------------

    public Answer(UUID questionId, String text) {
        this.text = text;
        this.questionId = questionId;
    }


    // ------------------------------------------------ PUBLIC METHODS -------------------------------------------------

    public void addToQuestion(UUID questionId) {
        this.questionId = questionId;
        this.validate();
    }
}
