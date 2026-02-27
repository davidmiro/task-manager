package dev.david.task_manager.service;

import dev.david.task_manager.entity.Task;
import dev.david.task_manager.repository.TaskManagerRepository;
import org.springframework.stereotype.Service;

@Service

public class TaskManagerService {
    private final TaskManagerRepository taskManagerRepository;

    public TaskManagerService(TaskManagerRepository taskManagerRepository) {
        this.taskManagerRepository = taskManagerRepository;
    }
    public void saveTask(Task task) {
        taskManagerRepository.save(task);
    }

}
