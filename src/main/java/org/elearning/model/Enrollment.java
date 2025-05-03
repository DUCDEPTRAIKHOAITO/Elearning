package org.elearning.model;

import jakarta.persistence.*;
import lombok.Data;
import org.elearning.enums.EnrollmentStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "enrollments")
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Instant enrollmentDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;

    private EnrollmentStatus status;
    private Instant createdBy;
}
