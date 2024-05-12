package com.example.crudDemo.dao;

import com.example.crudDemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);
    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByFirstName(String theFirstName);
    void update(Student student);
    void delete(Integer id);
    int deleteAll();
}