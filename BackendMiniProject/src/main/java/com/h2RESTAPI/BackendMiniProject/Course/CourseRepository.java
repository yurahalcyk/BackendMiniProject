package com.h2RESTAPI.BackendMiniProject.Course;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.name = ?1")
    Optional<Course> findCourseByName(String name);

}
