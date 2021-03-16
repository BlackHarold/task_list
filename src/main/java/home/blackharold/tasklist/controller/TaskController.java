package home.blackharold.tasklist.controller;

import home.blackharold.tasklist.entity.Task;
import home.blackharold.tasklist.repo.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller()
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("redutant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null) {
            return new ResponseEntity("missed param 'title'", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(taskRepository.save(task));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity("wrong id: " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("object with id: " + id + " deleted");
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Task task) {
        if (task.getId() == null && task.getId() == 0) {
            return new ResponseEntity("missed param: id must be not null", HttpStatus.NOT_ACCEPTABLE);
        }

        taskRepository.save(task);
        return new ResponseEntity("task with id: " + task.getId() + " updated", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        Task task;
        try {
            task = taskRepository.findById(id).get();
        } catch (NoSuchElementException nse) {
            return new ResponseEntity("No such element: " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(task);
    }
}
