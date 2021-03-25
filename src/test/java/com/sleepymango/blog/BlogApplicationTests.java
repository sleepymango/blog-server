package com.sleepymango.blog;
import com.sleepymango.blog.repository.ArticleRepository;
import com.sleepymango.blog.repository.UserRepository;
import com.sleepymango.blog.entity.Article;
import com.sleepymango.blog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private ArticleRepository articleDao;

    @Autowired
    private UserRepository userDao;

    @Test
    void getArticleAndUser() {
//        Article article = articleDao.findById(1L).orElse(null);
//        System.out.println(article);
    }

    @Test
    void addArticle(){
        User user = userDao.findById(1L).orElse(null);
        Article article = new Article();
        article.setArticleId(0L);
        article.setArticleTitle("java");
        article.setArticleContent("hello world");
        article.setArticleBanner("");
        article.setArticleTag("");
        article.setArticleView(0L);
        article.setArticleComment(0L);
        article.setArticleLike(0L);
        article.setArticlePublish(new Date());
        article.setArticleStatus("");
        article.setUser(user);
        article.setCategoryId(0L);
        articleDao.save(article);
    }

}
