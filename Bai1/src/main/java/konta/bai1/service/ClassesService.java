package konta.bai1.service;

import konta.bai1.entity.Classes;

import java.util.List;

public interface ClassesService {
    public List<Classes> findAll();
    public Classes findById(String id);
    public boolean add(Classes classes);
    public boolean edit(Classes classes);
    public boolean delete(String id);
    public List<Classes> findByUserName(String name);
}
