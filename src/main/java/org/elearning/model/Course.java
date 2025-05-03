package org.elearning.model;

import jakarta.persistence.*;
import lombok.Data;
import org.elearning.enums.CourseStatus;

import java.util.UUID;

@Entity
@Table(name = "course")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String description;
    private CourseStatus status;
}