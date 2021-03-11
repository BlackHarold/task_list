package home.blackharold.tasklist.controller;

import home.blackharold.tasklist.entity.Category;
import home.blackharold.tasklist.repo.CategoryRepository;
import home.blackharold.tasklist.search.CategorySearchValues;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/list")
    public List<Category> test() {
        return categoryRepository.findAll();
    }

    @GetMapping("/all")
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByTitleAsc();
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("redutant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null) {
            return new ResponseEntity("missed param 'title'", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category) {
        if (category.getId() == null && category.getId() == 0) {
            return new ResponseEntity("missed param: id must be not null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category category;
        try {
            category = categoryRepository.findById(id).get();
        } catch (NoSuchElementException nse) {
            return new ResponseEntity("No such element", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {
        return ResponseEntity.ok(categoryRepository.findByTitle(categorySearchValues.getText()));
    }
}
