package home.blackharold.tasklist.controller;

import home.blackharold.tasklist.entity.Category;
import home.blackharold.tasklist.repo.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
