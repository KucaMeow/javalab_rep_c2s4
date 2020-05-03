package ru.itis.javalabhw.model;

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
@Table(name = "tasks", schema = "public")
public class Task {
    @Id
    String id;
    String queueName;
    boolean ready;
}
