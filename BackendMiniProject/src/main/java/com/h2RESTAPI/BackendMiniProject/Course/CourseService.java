package com.h2RESTAPI.BackendMiniProject.Course;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses(){
        return (List<Course>) courseRepository.findAll();
    }

    public Course getCourseById(Long courseId){
        return courseRepository.findById(courseId).get();
    }

    public void addNewCourse(Course course){
        Optional<Course> courseOptional = courseRepository.findCourseByName(course.getName());
        if(courseOptional.isPresent()){
            throw new IllegalStateException("Cannot add course. It already exists by name.");
        }
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId){
        boolean exists = courseRepository.existsById(courseId);
        if(!exists){
            throw new IllegalStateException("course with id " + courseId + " does not exist");
        }
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(Long courseId, String name, Integer length){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new IllegalStateException(
                        "course with id " + courseId + " does not exist"));

        if(name!= null && !name.isEmpty() && !Objects.equals(course.getName(), name)){
            course.setName(name);
        }

        if(length!= null && !Objects.equals(course.getLength(), length)){
            course.setLength(length);
        }
    }

}
