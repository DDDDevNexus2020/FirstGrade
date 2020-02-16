package com.fg.content.question.model;

import com.fg.infrastructure.model.ValidatingModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Answer implements ValidatingModel<Answer> {

    // --------------------------------------------------- PROPERTIES --------------------------------------------------

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "com.fg.content.question.model.AnswerIdGenerator")
    private AnswerId id;

    @NotBlank(message = "The answer text is required.")
    private String text;

    @NotNull(message = "The answer must be associated with a question.")
    private QuestionId questionId;

    @Setter(AccessLevel.PROTECTED)
    private boolean isCorrect;


    // ------------------------------------------------- CONSTRUCTOR ---------------------------------------------------

    public Answer(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }


    // ------------------------------------------------ PUBLIC METHODS -------------------------------------------------

    public void addToQuestion(QuestionId questionId) {
        this.questionId = questionId;
        this.validate();
    }
}
