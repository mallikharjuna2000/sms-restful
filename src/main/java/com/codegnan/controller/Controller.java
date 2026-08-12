package com.codegnan.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.entity.Student;
import com.codegnan.service.StudentService;
/**
 * Day 38 — Student REST Controller.
 * @RestController = @Controller + @ResponseBody.
 * Every method's return value is serialised to JSON by Jackson automatically.
 * ResponseEntity<T> lets us set the HTTP status code explicitly.
 */
@RestController
@RequestMapping("/api/v1/students")
public class StudentRestController {
    private final StudentService service;
    @Autowired
    public StudentRestController(StudentService service) {
        this.service = service;
    }
    // ── GET /api/v1/students ── retrieve all students ──────────────────
    // Returns: 200 OK with JSON array of all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = service.getAll();
        return ResponseEntity.ok(students);            // 200 OK
    }
    // ── GET /api/v1/students/{id} ── retrieve one student ─────────────
    // Returns: 200 OK with the student JSON, or 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Optional<Student> found = service.getById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(found.get());     // 200 OK
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }
    // ── POST /api/v1/students ── create a new student ──────────────────
    // @RequestBody deserialises the JSON body sent by the client into Student.
    // Returns: 201 Created with the saved student JSON (includes generated id)
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student saved = service.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); // 201 Created
    }
    // ── PUT /api/v1/students/{id} ── update an existing student ────────
    // Returns: 200 OK with updated student JSON, or 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @RequestBody Student updatedData) {
        if (!service.exists(id)) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
        updatedData.setId(id);                        // ensure correct id is set
        Student saved = service.save(updatedData);
        return ResponseEntity.ok(saved);              // 200 OK
    }
    // ── DELETE /api/v1/students/{id} ── remove a student ───────────────
    // Returns: 204 No Content (successful delete, nothing to return)
    //          or 404 Not Found if the id does not exist
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        if (!service.exists(id)) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
        service.remove(id);
        return ResponseEntity.noContent().build();     // 204 No Content
    }
}

