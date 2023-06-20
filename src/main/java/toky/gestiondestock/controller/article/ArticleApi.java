package toky.gestiondestock.controller.article;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import toky.gestiondestock.dto.article.ArticleDto;

import java.util.List;

import static toky.gestiondestock.utils.Constants.APP_ROOT;

public interface ArticleApi {
    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findById(@PathVariable("idArticle") Long id);

    @GetMapping(value = APP_ROOT + "/articles/code/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String code);

    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{id}")
    void delete(@PathVariable Long id);
}
