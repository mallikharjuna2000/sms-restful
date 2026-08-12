StudentRepository.java  (com/codegnan/repository/StudentRepository.java)
package com.codegnan.repository;
import com.codegnan.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// JpaRepository provides: save(), findById(), findAll(), deleteById() — no SQL needed
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // No methods needed — JpaRepository covers all CRUD operations for Day 38
}












