package com.johurulislam.controller;

import com.johurulislam.model.Student;
import com.johurulislam.service.StudentService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping("/student")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return new ResponseEntity<>("save student", HttpStatus.CREATED);
    }
    @GetMapping("/student/name/{id}")
    public ResponseEntity<?> getName(@PathVariable Integer id) {
        String name= studentService.getStudentById(id);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Integer id) {
       Student student = studentService.getStudentInfoById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @GetMapping("/allstudents")
    public ResponseEntity<?> getAllStudent() {
        List<Student> allStudentInfo = studentService.getAllStudentInfo();
        return new ResponseEntity<>(allStudentInfo, HttpStatus.OK);
    }

}
