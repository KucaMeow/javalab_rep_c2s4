package ru.itis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckboxTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Lob
    String question;
    @Lob
    String correctAnswer;
    @OneToMany(mappedBy = "checkboxTest", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    List<CheckboxTestAnswer> checkboxTestAnswers;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "couserModuleId", nullable = true)
    @JsonIgnore
    CourseModule courseModule;

    @Transactional
    public List<CheckboxTestAnswer> getCheckboxTestAnswers() {
        Collections.shuffle(checkboxTestAnswers);
        return checkboxTestAnswers;
    }
}
