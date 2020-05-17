package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.config.security.details.UserDetailsImpl;
import ru.itis.models.Course;
import ru.itis.repositories.CousresRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CoursesService {

    @Autowired
    CousresRepository cousresRepository;
    @Override
    @Transactional
    public List<Course> getAllCourses() {
        return cousresRepository.findAll();
    }

    @Override
    public Course getCourse(long id) {
        Optional<Course> courseOptional = cousresRepository.find(id);
        return courseOptional.orElse(null);
    }

    @Override
    public Course createEmptyCourse() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
        Course newCourse = Course.builder()
                .author(email)
                .description("Empty description")
                .name("New course")
                .lessons(new ArrayList<>())
                .build();
        cousresRepository.save(newCourse);
        return newCourse;
    }

    @Transactional
    @Override
    public void updateCourseById(long id, String name, String description) {
        Course course = cousresRepository.find(id).orElse(null);
        if(course == null)
            return;
        course.setName(name);
        course.setDescription(description);
        cousresRepository.save(course);
    }
}
