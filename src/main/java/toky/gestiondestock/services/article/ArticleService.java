package toky.gestiondestock.services.article;


import toky.gestiondestock.dto.article.ArticleDto;

import java.util.List;

public interface ArticleService {
    ArticleDto save(ArticleDto articleDto);
    ArticleDto findById(Long id);
    ArticleDto findByCodeArticle(String code);
    List<ArticleDto> findAll();
    void delete(Long id);
}
