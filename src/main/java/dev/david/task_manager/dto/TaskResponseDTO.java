package dev.david.task_manager.dto;

import dev.david.task_manager.enums.TaskPriority;
import dev.david.task_manager.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponseDTO {
    private Long id;
    private String title;
    private TaskStatus status;
    private TaskPriority priority;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
}
