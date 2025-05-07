package org.elearning.model;

import jakarta.persistence.*;
import lombok.Data;
import org.elearning.enums.LearnerStatus;

import java.util.UUID;

@Entity
@Table(name = "learner")
@Data
public class Learner {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LearnerStatus status;
}
