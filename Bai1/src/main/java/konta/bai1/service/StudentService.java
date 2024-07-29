package konta.bai1.service;

import konta.bai1.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(Integer empId);
    boolean add(Student emp);
    boolean edit(Student emp);
    boolean delete(Integer empId);
    List<Student> findByName(String empName);
}
