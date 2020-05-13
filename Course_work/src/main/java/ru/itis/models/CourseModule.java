package ru.itis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "module")
public class CourseModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int orderNumber;
    @Enumerated(EnumType.STRING)
    ModuleType moduleType;
    @OneToOne(mappedBy = "courseModule", cascade = CascadeType.ALL)
    CodeTask codeTask;
    @OneToOne(mappedBy = "courseModule", cascade = CascadeType.ALL)
    LearningText learningText;
    @OneToOne(mappedBy = "courseModule", cascade = CascadeType.ALL)
    CheckboxTest checkboxTest;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    Lesson lesson;
}
