package toky.gestiondestock.controller.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import toky.gestiondestock.dto.article.ArticleDto;
import toky.gestiondestock.services.article.ArticleService;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi{
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        return articleService.save(articleDto);
    }

    @Override
    public ArticleDto findById(Long id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String code) {
        return articleService.findByCodeArticle(code);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Long id) {
        articleService.delete(id);
    }
}
