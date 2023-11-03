package com.h2RESTAPI.BackendMiniProject.Course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CourseConfig {

    @Bean
    CommandLineRunner courseCommandLineRunner(CourseRepository repository){
        return args -> {
            Course economics = new Course("Economics", 3);
            Course maths = new Course("Maths", 3);
            Course meng = new Course("Mechanical Engineering", 4);
            repository.saveAll(List.of(economics, maths, meng));
        };
    }

}
