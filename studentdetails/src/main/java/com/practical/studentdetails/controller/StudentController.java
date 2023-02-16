package com.practical.studentdetails.controller;

import com.practical.studentdetails.entity.GenericResponse;
import com.practical.studentdetails.entity.Student;
import com.practical.studentdetails.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<GenericResponse> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(new GenericResponse(true, "Student created successfully", studentService.addStudent(student), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GenericResponse> updateStudent(@RequestBody Student student) {
        return new ResponseEntity<>(new GenericResponse(true, "Student updated successfully", studentService.updateStudent(student), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GenericResponse> getStudentsFailedInEnglish() {
        return new ResponseEntity<>(new GenericResponse(true, "Students fetched successfully", studentService.getStudentsFailedInEnglish(), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping("/totalMarks/{id}")
    public ResponseEntity<GenericResponse> getTotalMarks(@PathVariable Long id) {
        return new ResponseEntity<>(new GenericResponse(true, "Total Marks of Student fetched successfully", studentService.getTotalMarks(id), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping("/getName")
    public ResponseEntity<GenericResponse> getSortedMarksStudent() {
        return new ResponseEntity<>(new GenericResponse(true, "Total Marks of Student fetched successfully", studentService.getSortedMarksStudent(), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }


}
