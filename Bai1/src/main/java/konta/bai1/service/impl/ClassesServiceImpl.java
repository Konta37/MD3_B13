package konta.bai1.service.impl;

import konta.bai1.dao.impl.ClassDAOImpl;
import konta.bai1.entity.Classes;
import konta.bai1.service.ClassesService;

import java.util.List;

public class ClassesServiceImpl implements ClassesService {

    @Override
    public List<Classes> findAll() {
        return new ClassDAOImpl().findAll();
    }

    @Override
    public Classes findById(String id) {
        return new ClassDAOImpl().findById(id);
    }

    @Override
    public boolean add(Classes classes) {
        return new ClassDAOImpl().add(classes);
    }

    @Override
    public boolean edit(Classes classes) {
        return new ClassDAOImpl().edit(classes);
    }

    @Override
    public boolean delete(String id) {
        return new ClassDAOImpl().delete(id);
    }

    @Override
    public List<Classes> findByUserName(String name) {
        return new ClassDAOImpl().findByName(name);
    }
}
