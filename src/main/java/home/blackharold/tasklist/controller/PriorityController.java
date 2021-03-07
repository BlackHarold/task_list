package home.blackharold.tasklist.controller;

import home.blackharold.tasklist.entity.Priority;
import home.blackharold.tasklist.repo.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
