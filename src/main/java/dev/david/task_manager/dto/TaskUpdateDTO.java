package dev.david.task_manager.dto;

import dev.david.task_manager.enums.TaskPriority;
import dev.david.task_manager.enums.TaskStatus;
import lombok.Data;

@Data
public class TaskUpdateDTO {
    private String title;
    private TaskStatus status;
    private TaskPriority priority;
    private String description;

}
