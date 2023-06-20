package toky.gestiondestock.services.categorie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toky.gestiondestock.dao.categorie.CategoryRepository;
import toky.gestiondestock.dto.categorie.CategoryDto;
import toky.gestiondestock.exception.ErrorCodes;
import toky.gestiondestock.exception.InvalidException;
import toky.gestiondestock.exception.NotFoundException;
import toky.gestiondestock.model.categorie.Category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository category;

    @Autowired
    public CategoryServiceImpl(CategoryRepository category) {
        this.category = category;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        if(categoryDto == null) {
            log.error("Category is not valid",categoryDto);
            throw new InvalidException("Le Categorie n'est pas valide", ErrorCodes.CATEGORIE_NOT_VALID);
        }
        return CategoryDto.fromEntity(category.save(CategoryDto.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto findById(Long id) {
        if (id == null) {
            log.error("Categorie ID is Null");
            return null;
        }
        Optional<Category> ctgr = category.findById(id);
        CategoryDto categoryDto = CategoryDto.fromEntity(ctgr.get());
        return Optional.of(categoryDto).orElseThrow(()->new NotFoundException("Aucun Categorie avec l'ÍD = " +
                id + "n'etes trouve dans la BDD",ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto findByCodeCategory(String code) {
        if (code == null) {
            log.error("Code Categorie is Null");
            return null;
        }
        Optional<Category> ctgre = category.findCategoryByCode(code);
        CategoryDto categoryDto = CategoryDto.fromEntity(ctgre.get());
        return Optional.of(categoryDto).orElseThrow(()->new NotFoundException("Aucun Categorie avec le Code = " +
                code + "n'etes trouve dans la BDD",ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAll() {
        return  category.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Categorie ID is Null");
        }
        category.deleteById(id);
    }
}
