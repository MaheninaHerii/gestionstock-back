package toky.gestiondestock.services.categorie;


import toky.gestiondestock.dto.categorie.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto findById(Long id);
    CategoryDto findByCodeCategory(String code);
    List<CategoryDto> findAll();
    void delete(Long id);
}
