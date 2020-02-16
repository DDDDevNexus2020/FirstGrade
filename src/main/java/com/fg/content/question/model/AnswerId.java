package com.fg.content.question.model;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class AnswerId implements Serializable {

    private final UUID id;

}
