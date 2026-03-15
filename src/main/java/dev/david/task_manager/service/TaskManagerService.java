package dev.david.task_manager.service;

import dev.david.task_manager.entity.Task;
import dev.david.task_manager.repository.TaskManagerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

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
        return taskManagerRepository.save(task);
    }
    public Task updateById(Long id, Task task) {
        taskManagerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return taskManagerRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskManagerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        taskManagerRepository.deleteById(id);
    }
}
