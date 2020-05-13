package ru.itis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckboxTestAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Lob
    String answer;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "checkboxTestId")
    @JsonIgnore
    CheckboxTest checkboxTest;
}
