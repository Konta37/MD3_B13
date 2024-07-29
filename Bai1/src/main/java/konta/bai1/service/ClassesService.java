package konta.bai1.service;

import konta.bai1.entity.Classes;

import java.util.List;

public interface ClassesService {
    public List<Classes> findAll();
    public Classes findById(Integer id);
    public boolean add(Classes classes);
    public boolean edit(Classes classes);
    public boolean delete(Integer id);
    public List<Classes> findByUserName(String name);
}
