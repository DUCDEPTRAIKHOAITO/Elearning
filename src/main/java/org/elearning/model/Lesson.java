package org.elearning.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "lesson")
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String name;  // Added field for name
    private String description;
    private String referenceLink;

    private Instant lessonDate;

    // Getters and Setters are automatically handled by Lombok's @Data annotation
}
