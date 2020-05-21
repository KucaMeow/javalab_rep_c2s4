package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonInformationDto {
    String authorEmail;
    int moduleCount;
}
