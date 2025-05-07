package org.elearning.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "assignment")
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false, length = 200)
    private String name;

    // The date field is now used
    private Instant date;

    private String description;

    // Getter for date
    public Instant getDate() {
        return date;
    }

    // Setter for date
    public void setDate(Instant date) {
        this.date = date;
    }
}
