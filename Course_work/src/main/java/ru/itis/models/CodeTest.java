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
public class CodeTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Lob
    String input;
    @Lob
    String output;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "taskId", nullable = false)
    @JsonIgnore
    CodeTask codeTask;
}
