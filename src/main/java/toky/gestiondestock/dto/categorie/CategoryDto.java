package toky.gestiondestock.dto.categorie;


import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.article.ArticleDto;
import toky.gestiondestock.model.categorie.Category;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CategoryDto {

    private long id;

    private String code;

    private String designation;

    private List<ArticleDto> articleList;

    public static CategoryDto fromEntity(Category category) {
        if (category == null) {
            //TODO throw  error exception
            return null;
        }

        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .articleList(category.getArticleList() != null ?
                        category.getArticleList().stream()
                                .map(ArticleDto::fromEntity)
                                .collect(Collectors.toList()) : null).build();
    }

    public static Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            //TODO throw error exception
            return null;
        }

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(category.getDesignation());
        category.setArticleList(categoryDto.getArticleList() != null ?
                categoryDto.getArticleList().stream()
                        .map(ArticleDto::toEntity)
                        .collect(Collectors.toList()) : null);
        return category;
    }
}
