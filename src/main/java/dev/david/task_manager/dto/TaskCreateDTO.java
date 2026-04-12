package dev.david.task_manager.dto;

import dev.david.task_manager.enums.TaskPriority;
import dev.david.task_manager.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateDTO {

    @NotBlank(message = "Title is required")
    private String title;
    private TaskStatus status;
    @NotNull
    private TaskPriority priority;
    private String description;
}
