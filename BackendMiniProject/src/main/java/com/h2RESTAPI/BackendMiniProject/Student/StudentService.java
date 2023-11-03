package com.h2RESTAPI.BackendMiniProject.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    // reference to student repository.
    private final StudentRepository studentRepository;

    // student service constructor
    // autowiring the student repository into the student service.
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    // function to return list of students.
    public List<Student> getStudents(){
        return (List<Student>) studentRepository.findAll();
    }

    // function to get student by id.
    public Student getStudentById(Long id){
        return studentRepository.findById(id).get();
    }

    // function to add new student.
    // first, check completed to see if student email exists.
    // if so, exception is thrown and student not added.
    // no exception -> student added.
    public void addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Cannot add student. Email taken.");
        }
        studentRepository.save(student);
    }

    // function to delete student by id.
    // first, check completed to see if id exists.
    // if so, student is deleted from database.
    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    // function to update student
    // transactional annotation used to ensure changes only committed to database if no error occurs
    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        // check to see if student exists via id. if not, throw error.
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(
                        "student with id " + studentId + " does not exist."));

        // if name not null, length greater than zero, and not the same as current name -> set new name
        if(name!= null && !name.isEmpty() && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        // if email not null, length greater than zero, and not the same as current name -> set new name
        if(email!= null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            // check to see if email taken
            if(studentOptional.isPresent()){
                throw new IllegalStateException("cannot update email as this email is taken already.");
            }
            student.setEmail(email);
        }
    }
}