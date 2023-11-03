package com.h2RESTAPI.BackendMiniProject.Enrolment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/enrolment")
public class EnrolmentController {

    private final EnrolmentService enrolmentService;

    @Autowired
    public EnrolmentController(EnrolmentService enrolmentService){this.enrolmentService = enrolmentService;}

    @GetMapping
    public List<Enrolment> getEnrolments(){return enrolmentService.getEnrolments();}

    @GetMapping(path = "{enrolmentId}")
    public Enrolment getEnrolmentById(@PathVariable("enrolmentId") Long enrolmentId){return enrolmentService.getEnrolmentById(enrolmentId);}

    @PostMapping
    public void addEnrolment(@RequestBody Enrolment enrolment){enrolmentService.addEnrolment(enrolment);}


    @DeleteMapping(path="{enrolmentId}")
    public void deleteEnrolment(@PathVariable("enrolmentId") Long enrolmentId){enrolmentService.deleteEnrolment(enrolmentId);}
}
