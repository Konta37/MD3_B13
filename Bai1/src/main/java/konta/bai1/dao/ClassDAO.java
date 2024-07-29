package konta.bai1.dao;

import konta.bai1.entity.Classes;

import java.util.List;

public interface ClassDAO {
    List<Classes> findAll();
    Classes findById(String classID);
    boolean add(Classes classes);
    boolean edit(Classes classes);
    boolean delete(String classID);
    List<Classes> findByName(String className);
}
