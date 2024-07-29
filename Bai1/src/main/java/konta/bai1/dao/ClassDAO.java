package konta.bai1.dao;

import konta.bai1.entity.Classes;

import java.util.List;

public interface ClassDAO {
    List<Classes> findAll();
    Classes findById(Integer classID);
    boolean add(Classes classes);
    boolean edit(Classes classes);
    boolean delete(Integer classID);
    List<Classes> findByName(String className);
}
