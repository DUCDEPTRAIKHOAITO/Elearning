package org.elearning.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "submission")
public class Submission {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment; // Relationship with Assignment

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner; // Relationship with Learner

    private String date;
    private String grade;

    // Getters, Setters, Constructors
}
