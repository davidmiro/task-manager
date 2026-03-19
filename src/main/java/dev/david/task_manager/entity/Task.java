package dev.david.task_manager.entity;

import dev.david.task_manager.enums.TaskPriority;
import dev.david.task_manager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

}
