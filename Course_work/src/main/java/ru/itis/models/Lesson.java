package ru.itis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int orderNumber;
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("orderNumber")
    Collection<CourseModule> courseModules;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "courseId")
    @JsonIgnore
    Course course;

    Date lastTimeEdit;

    @PostUpdate
    public void postUpdate() {
        lastTimeEdit = new Date();
    }
}
