package toky.gestiondestock.controller.categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import toky.gestiondestock.dto.categorie.CategoryDto;
import toky.gestiondestock.services.categorie.CategoryService;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryService.findById(id);
    }

    @Override
    public CategoryDto findByCodeCategory(String code) {
        return categoryService.findByCodeCategory(code);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Long id) {
        categoryService.delete(id);
    }
}
