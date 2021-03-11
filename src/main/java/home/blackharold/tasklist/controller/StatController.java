package home.blackharold.tasklist.controller;

import com.sun.istack.Nullable;
import home.blackharold.tasklist.entity.Stat;
import home.blackharold.tasklist.repo.StatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/stat")
public class StatController {

    private StatRepository statRepository;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Stat> findById(@PathVariable @Nullable Long id) {
        long innerId = 1l;
        if (id == null || id == 0) {
            ResponseEntity.ok(statRepository.findById(innerId).get());
        }

        return ResponseEntity.ok(statRepository.findById(id).get());
    }
}
