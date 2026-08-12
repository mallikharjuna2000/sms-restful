package com.codegnan.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository repo;
    @Autowired
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }
    // Save new student or update existing one
    public Student save(Student s) { return repo.save(s); }
    // Retrieve all students
    public List<Student> getAll() { return repo.findAll(); }
    // Retrieve one student by id — returns Optional (may be absent)
    public Optional<Student> getById(int id) { return repo.findById(id); }
    // Remove student by id
    public void remove(int id) { repo.deleteById(id); }
    // Check whether a student with the given id exists
    public boolean exists(int id) { return repo.existsById(id); }
}
