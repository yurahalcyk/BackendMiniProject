package com.h2RESTAPI.BackendMiniProject.Enrolment;

import com.h2RESTAPI.BackendMiniProject.Course.CourseRepository;
import com.h2RESTAPI.BackendMiniProject.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EnrolmentService {

    private final EnrolmentRepository enrolmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrolmentService(EnrolmentRepository enrolmentRepository,
                            StudentRepository studentRepository,
                            CourseRepository courseRepository){
        this.enrolmentRepository = enrolmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Enrolment> getEnrolments(){return (List<Enrolment>) enrolmentRepository.findAll();}

    public Enrolment getEnrolmentById(Long enrolmentId){return enrolmentRepository.findById(enrolmentId).get();}

    public void addEnrolment(Enrolment enrolment){
        if(!studentRepository.existsById(enrolment.getStudentId())){
            throw new IllegalStateException("Student with id " + enrolment.getStudentId() + " does not exist");
        }
        if(!courseRepository.existsById(enrolment.getCourseId())){
            throw new IllegalStateException("Course with id " + enrolment.getCourseId() + " does not exist");
        }
        enrolmentRepository.save(enrolment);
    }

    public void deleteEnrolment(Long enrolmentId){
        boolean exists = enrolmentRepository.existsById(enrolmentId);
        if(!exists){
            throw new IllegalStateException("Enrolment with id " + enrolmentId + " does not exist");
        }
        enrolmentRepository.deleteById(enrolmentId);
    }




}
