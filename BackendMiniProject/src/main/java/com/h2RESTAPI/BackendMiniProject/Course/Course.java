package com.h2RESTAPI.BackendMiniProject.Course;

import com.h2RESTAPI.BackendMiniProject.Student.Student;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer length;

    // Constructors

    public Course() {
    }

    public Course(Long id, String name, Integer length) {
        this.id = id;
        this.name = name;
        this.length = length;
    }

    public Course(String name, Integer length) {
        this.name = name;
        this.length = length;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    //toString

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}
