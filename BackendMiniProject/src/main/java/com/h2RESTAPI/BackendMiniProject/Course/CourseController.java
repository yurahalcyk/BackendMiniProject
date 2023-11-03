package com.h2RESTAPI.BackendMiniProject.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    @GetMapping(path="{courseId}")
    public Course getCourseById(@PathVariable("courseId") Long courseId){
        return courseService.getCourseById(courseId);
    }

    @PostMapping
    public void addCourse(@RequestBody Course course){
        courseService.addNewCourse(course);
    }

    @DeleteMapping(path="{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId){
        courseService.deleteCourse(courseId);
    }

    @PutMapping(path="{courseId}")
    public void updateCourse(
            @PathVariable Long courseId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer length){
        courseService.updateCourse(courseId, name, length);
    }
}
