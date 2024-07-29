package konta.bai1.dao;

import konta.bai1.entity.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> findAll();
    Student findById(Integer studentId);
    boolean add(Student student);
    boolean edit(Student student);
    boolean delete(Integer studentId);
    List<Student> findByName(String studentName);
}
