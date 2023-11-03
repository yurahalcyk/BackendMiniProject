package com.h2RESTAPI.BackendMiniProject.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student a = new Student(1L, "a", "studentA@gmail.com", LocalDate.of(1999, Month.MAY, 6));
            Student b = new Student(2L, "b", "studentB@gmail.com", LocalDate.of(1997, Month.JUNE, 12));
            Student c = new Student(3L, "c", "studentC@gmail.com", LocalDate.of(1995, Month.JANUARY, 18));
            repository.saveAll(List.of(a, b, c));
        };
    }

}
