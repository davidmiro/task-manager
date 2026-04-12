package dev.david.task_manager.mapper;

import dev.david.task_manager.dto.TaskCreateDTO;
import dev.david.task_manager.dto.TaskResponseDTO;
import dev.david.task_manager.entity.Task;
import org.springframework.stereotype.Component;


public class TaskMapper {
    public static TaskResponseDTO toResponseDTO(Task task) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setStatus(task.getStatus());
        taskResponseDTO.setPriority(task.getPriority());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setCreatedAt(task.getCreatedAt());
        taskResponseDTO.setCompletedAt(task.getCompletedAt());
        return taskResponseDTO;
    }
    public static Task toEntity(TaskCreateDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDescription(dto.getDescription());
        return task;
    }
}
