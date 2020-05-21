package ru.itis.repositories;


import ru.itis.dto.LessonInformationDto;
import ru.itis.models.Lesson;

import java.util.Optional;

public interface LessonsRepository extends CrudRepository<Long, Lesson> {
    Optional<LessonInformationDto> getLessonInformationDtoFromLesson (long id);
}
