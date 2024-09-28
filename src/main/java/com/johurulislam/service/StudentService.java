package com.johurulislam.service;

import com.johurulislam.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class StudentService {
    private final JdbcTemplate jdbcTemplate;
    public StudentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveStudent(Student student) {
        String sql="insert into student (id, name, gender) values (?,?,?) ";
        jdbcTemplate.update(sql, student.getId(), student.getName(), student.getGender());

    }

    public String getStudentById(Integer id) {
        String sql="SELECT name from student where id=? ";
        return  jdbcTemplate.queryForObject(sql,String.class, id);

    }
    public Student getStudentInfoById(Integer id) {
        String sql="SELECT * from student where id=? ";
        return  jdbcTemplate.queryForObject(sql,(result, rowNum) -> {
            Student student = new Student();
            student.setId(result.getInt("id"));
            student.setName(result.getString("name"));
            student.setGender(result.getString("gender"));
            return student;
        }, id);
    }
    public List<Student> getAllStudentInfo(){
        String sql="SELECT * from student";
        return jdbcTemplate.query(sql,(result,rowNum)->{
            Student student = new Student();
            student.setId(result.getInt("id"));
            student.setName(result.getString("name"));
            student.setGender(result.getString("gender"));
            return student;
        });
    }



}
