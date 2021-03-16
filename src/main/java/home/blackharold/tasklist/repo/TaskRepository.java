package home.blackharold.tasklist.repo;

import home.blackharold.tasklist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
