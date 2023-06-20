package toky.gestiondestock.services.article;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import toky.gestiondestock.dto.article.ArticleDto;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;
    @Test
    public void shouldSaveArticle() {
        ArticleDto articleDto = ArticleDto.builder()
                .codeArticle("Art test")
                .designation("Designation")
                .build();

        ArticleDto art = articleService.save(articleDto);

        assertNotNull(art);
        assertNotNull(art.getId());
        assertEquals(articleDto.getCodeArticle(), art.getCodeArticle());
        assertEquals(articleDto.getDesignation(), art.getDesignation());
        assertEquals(articleDto.getCodeArticle(), art.getCodeArticle());

    }
}