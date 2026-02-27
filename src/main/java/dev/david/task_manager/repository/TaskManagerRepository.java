package dev.david.task_manager.repository;

import dev.david.task_manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManagerRepository extends JpaRepository<Task, Long> {
}
