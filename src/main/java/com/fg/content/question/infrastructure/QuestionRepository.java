package com.fg.content.question.infrastructure;

import com.fg.content.question.model.Question;
import com.fg.content.question.model.QuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, QuestionId> {
}
