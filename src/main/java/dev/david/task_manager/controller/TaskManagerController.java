package dev.david.task_manager.controller;

import dev.david.task_manager.dto.TaskCreateDTO;
import dev.david.task_manager.dto.TaskResponseDTO;
import dev.david.task_manager.dto.TaskUpdateDTO;
import dev.david.task_manager.service.TaskManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskManagerController {
    private final TaskManagerService taskManagerService;

    public TaskManagerController(TaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    @GetMapping("/")
    public List<TaskResponseDTO> getTasks() {
        return taskManagerService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskManagerService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskCreateDTO taskDto) {
        TaskResponseDTO savedTask = taskManagerService.createTask(taskDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTask);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskUpdateDTO taskDto) {
        return ResponseEntity.ok(taskManagerService.updateById(id, taskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskManagerService.deleteTaskById(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
