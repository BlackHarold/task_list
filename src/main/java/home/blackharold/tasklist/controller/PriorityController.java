package home.blackharold.tasklist.controller;

import home.blackharold.tasklist.entity.Priority;
import home.blackharold.tasklist.repo.PriorityRepository;
import home.blackharold.tasklist.search.PrioritySearchValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    @Autowired
    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/list")
    public List<Priority> test() {
        return priorityRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("redutant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getColor() == null) {
            return new ResponseEntity("missed param 'title' or 'color'", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {
        System.out.println("priority id: " + priority.getId());
        if (priority.getId() == null && priority.getId() == 0) {
            return new ResponseEntity("missed param: id must be not null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Priority> getById(@PathVariable Long id) {
        Priority priority;
        try {
            priority = priorityRepository.findById(id).get();
        } catch (NoSuchElementException nse) {
            return new ResponseEntity("No such element: " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(priority);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            priorityRepository.deleteById(id);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity("wrong id: " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("object with id: " + id + " deleted");
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> findAllByColor(@RequestBody PrioritySearchValues prioritySearchValues) {
        return ResponseEntity.ok(priorityRepository.findByColor(prioritySearchValues.getTitle(), prioritySearchValues.getColor()));
    }
}
