package com.codegnan.entity;
import jakarta.persistence.*;
/**
 * Day 38 — JPA entity mapped to the 'student' table.
 * Jackson serialises this to JSON when returned from @RestController.
 */
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String address;
    // ── Constructors ───────────────────────────────────────────────────
    public Student() {}
    public Student(String name, String email, String address) {
        this.name    = name;
        this.email   = email;
        this.address = address;
    }
    // ── Getters and setters ────────────────────────────────────────────
    public int    getId()      { return id;      }
    public String getName()    { return name;    }
    public String getEmail()   { return email;   }
    public String getAddress() { return address; }
    public void setId(int id)           { this.id      = id;      }
    public void setName(String name)    { this.name    = name;    }
    public void setEmail(String email)  { this.email   = email;   }
    public void setAddress(String addr) { this.address = addr;    }
}
