package dev.david.task_manager.service;

import dev.david.task_manager.entity.Task;
import dev.david.task_manager.enums.TaskStatus;
import dev.david.task_manager.repository.TaskManagerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class TaskManagerService {
    private final TaskManagerRepository taskManagerRepository;

    public TaskManagerService(TaskManagerRepository taskManagerRepository) {
        this.taskManagerRepository = taskManagerRepository;
    }

    public void save() {
        taskManagerRepository.save(new Task());
    }

    public List<Task> getAllTasks() {
        return taskManagerRepository.findAll();
    }

    public void findById(Long id) {
        taskManagerRepository.findById(id);
    }

    public Task getTaskById(Long id) {
        Optional<Task> taskOptional = taskManagerRepository.findById(id);
        return taskManagerRepository.
                findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        return taskManagerRepository.save(task);
    }
    public Task updateById(Long id, Task task) {
        Task taskToUpdate = taskManagerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setStatus(task.getStatus());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setPriority(task.getPriority());
        if (task.getStatus() == TaskStatus.DONE) {
            taskToUpdate.setCompletedAt(LocalDateTime.now());
        } else taskToUpdate.setCompletedAt(null);
        return taskManagerRepository.save(taskToUpdate);
    }

    public void deleteTaskById(Long id) {
        taskManagerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        taskManagerRepository.deleteById(id);
    }
}
