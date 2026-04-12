package dev.david.task_manager.service;

import dev.david.task_manager.dto.TaskCreateDTO;
import dev.david.task_manager.dto.TaskResponseDTO;
import dev.david.task_manager.dto.TaskUpdateDTO;
import dev.david.task_manager.entity.Task;
import dev.david.task_manager.enums.TaskStatus;
import dev.david.task_manager.mapper.TaskMapper;
import dev.david.task_manager.repository.TaskManagerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TaskManagerService {
    private final TaskManagerRepository taskManagerRepository;

    public TaskManagerService(TaskManagerRepository taskManagerRepository) {
        this.taskManagerRepository = taskManagerRepository;
    }

    public void save() {
        taskManagerRepository.save(new Task());
    }

    public List<TaskResponseDTO> getAllTasks() {
        List<Task> results = taskManagerRepository.findAll();
        return results.stream()
                .map(TaskMapper::toResponseDTO)
                .toList();
    }

    public void findById(Long id) {
        taskManagerRepository.findById(id);
    }

    public TaskResponseDTO getTaskById(Long id) {
        //Optional<Task>;
        return taskManagerRepository.findById(id).
                map(TaskMapper::toResponseDTO).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public TaskResponseDTO createTask(TaskCreateDTO dto) {
        Task task = TaskMapper.toEntity(dto);
        task.setCreatedAt(LocalDateTime.now());
        Task savedTask = taskManagerRepository.save(task);
        return TaskMapper.toResponseDTO(savedTask);
    }

    public TaskResponseDTO updateById(Long id, TaskUpdateDTO updateDTO) {
        Task existingTask = taskManagerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (updateDTO.getDescription() != null) {
            existingTask.setDescription(updateDTO.getDescription());
        }
        if (updateDTO.getPriority() != null) {
            existingTask.setPriority(updateDTO.getPriority());
        }
        if (updateDTO.getTitle() != null) {
            existingTask.setTitle(updateDTO.getTitle());
        }
        if (updateDTO.getStatus() != null) {
            TaskStatus oldStatus = existingTask.getStatus();
            TaskStatus newStatus = updateDTO.getStatus();

            if (newStatus == TaskStatus.DONE && oldStatus != TaskStatus.DONE) {
                existingTask.setCompletedAt(LocalDateTime.now());
            } else if (newStatus != TaskStatus.DONE &&  oldStatus == TaskStatus.DONE) {
                existingTask.setCompletedAt(null);
            }
            existingTask.setStatus(newStatus);
        }
        return TaskMapper.toResponseDTO(taskManagerRepository.save(existingTask));
    }

    public void deleteTaskById(Long id) {
        taskManagerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        taskManagerRepository.deleteById(id);
    }
}