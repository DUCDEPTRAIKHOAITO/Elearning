package org.elearning.model;

import jakarta.persistence.*;
import org.elearning.enums.UserStatus;


import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;  // Sử dụng enum UserStatus

    private Instant createdTime;
    private Instant updatedTime;
    private Instant updatedBy;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    // Getters and Setters
    public User(String name, String email, String password, UserStatus status, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
        this.createdTime = Instant.now();  // Set created time when object is created
        this.updatedTime = Instant.now();  // Set updated time when object is created
    }

    // PrePersist method to automatically set created time
    @PrePersist
    public void onCreate() {
        this.createdTime = Instant.now();
        this.updatedTime = Instant.now();
    }

    // PreUpdate method to automatically set updated time
    @PreUpdate
    public void onUpdate() {
        this.updatedTime = Instant.now();
    }

    // test
}

