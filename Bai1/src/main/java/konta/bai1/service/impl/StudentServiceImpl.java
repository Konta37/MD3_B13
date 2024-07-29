package konta.bai1.service.impl;

import konta.bai1.dao.impl.StudentDAOImpl;
import konta.bai1.entity.Student;
import konta.bai1.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> findAll() {
        return new StudentDAOImpl().findAll();
    }

    @Override
    public Student findById(Integer empId) {
        return new StudentDAOImpl().findById(empId);
    }

    @Override
    public boolean add(Student emp) {
        return new StudentDAOImpl().add(emp);
    }

    @Override
    public boolean edit(Student emp) {
        return new StudentDAOImpl().edit(emp);
    }

    @Override
    public boolean delete(Integer empId) {
        return new StudentDAOImpl().delete(empId);
    }

    @Override
    public List<Student> findByName(String empName) {
        return new StudentDAOImpl().findByName(empName);
    }
}
