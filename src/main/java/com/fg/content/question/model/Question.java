package com.fg.content.question.model;

import com.fg.infrastructure.model.ValidatingModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Question implements ValidatingModel<Question> {

    // --------------------------------------------------- PROPERTIES --------------------------------------------------

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message = "The question text is required.")
    private String text;

    private int gradeLevel;

    /**
     * A semicolon-delimited list of tags.
     */
    private String tags;


    // ------------------------------------------------- CONSTRUCTOR ---------------------------------------------------

    public Question(String text, int gradeLevel, List<String> tags) {
        this.text = text;
        this.changeFilterCriteria(gradeLevel, tags);
    }


    // ------------------------------------------------ PUBLIC METHODS -------------------------------------------------

    /**
     * Change criteria for finding this question.
     */
    public void changeFilterCriteria(int gradeLevel, List<String> tags) {
        this.setGradeLevel(gradeLevel);
        this.setTags(tags);
    }

    /**
     * Retrieves the tags for this Question.
     */
    public List<String> getTags() {
        return Arrays.asList(this.tags.split(";"));
    }


    // ----------------------------------------------- PRIVATE METHODS -------------------------------------------------

    /**
     * Sets the tags for this Question to the given tags.
     */
    public void setTags(List<String> tags) {
        this.tags = StringUtils.join(tags, ';');
    }

}
