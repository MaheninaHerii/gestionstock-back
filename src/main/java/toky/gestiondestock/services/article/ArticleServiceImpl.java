package toky.gestiondestock.services.article;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toky.gestiondestock.dao.article.ArticleRepository;
import toky.gestiondestock.dto.article.ArticleDto;
import toky.gestiondestock.exception.ErrorCodes;
import toky.gestiondestock.exception.InvalidException;
import toky.gestiondestock.exception.NotFoundException;
import toky.gestiondestock.model.article.Article;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository repository) {
        this.articleRepository = repository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        if(articleDto == null) {
            log.error("Article is not valid",articleDto);
            throw new InvalidException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID);
        }
        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
    }

    @Override
    public ArticleDto findById(Long id) {
        if (id == null) {
            log.error("Article ID is Null");
            return null;
        }
        Optional<Article> article = articleRepository.findById(id);
        ArticleDto articleDto = ArticleDto.fromEntity(article.get());
        return Optional.of(articleDto).orElseThrow(()->new NotFoundException("Aucun Article avec l'ÍD = " +
                id + "n'etes trouve dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCodeArticle(String code) {
        if (code == null) {
            log.error("Code Article is Null");
            return null;
        }
        Optional<Article> article = articleRepository.findArticleByCodeArticle(code);
        ArticleDto articleDto = ArticleDto.fromEntity(article.get());
        return Optional.of(articleDto).orElseThrow(()->new NotFoundException("Aucun Article avec le Code = " +
                code + "n'etes trouve dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Article ID is Null");
        }
        articleRepository.deleteById(id);
    }
}
