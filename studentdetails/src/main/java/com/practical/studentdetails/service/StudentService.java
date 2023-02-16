package com.practical.studentdetails.service;

import com.practical.studentdetails.entity.Result;
import com.practical.studentdetails.entity.Student;
import com.practical.studentdetails.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student addStudent(@RequestBody Student student) {

        if (studentRepo.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Student Already Exist");
        }
        return studentRepo.save(student);
    }

    public Student updateStudent(@RequestBody Student student) {

        Student updatedStudent = studentRepo.findById(student.getId()).orElseThrow(() -> new RuntimeException("Student doesn't exsit"));
        updatedStudent.setName(student.getName());
        updatedStudent.setAddress(student.getAddress());
        updatedStudent.setEmail(student.getEmail());
        return updatedStudent;
    }

    public List<Student> getStudentsFailedInEnglish() {
        return studentRepo.getStudentsFailedInEnglish();
    }

    public String getTotalMarks(Long id) {

        Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student doesn't exsit"));
        List<Result> result = student.getResults();
        int totalMarks = 0;
        for (Result result1 : result) {
            totalMarks = totalMarks + result1.getMarks();
        }
        return "Total Marks of student " + student.getName() + " is " + totalMarks;
    }

    public List<String> getSortedMarksStudent() {

        List<Student> student = studentRepo.getSortedMarksStudent();
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (Student student1 : student) {
            int totalMarks = studentRepo.getTotal(student1.getId());
            String name = student1.getName();
            hashMap.put(name, totalMarks);
        }
        List<String> names = new ArrayList<>();
        for (Map.Entry m : hashMap.entrySet()) {
            names.add((String) m.getKey());
        }
        return names.stream().sorted().toList();
    }


}
