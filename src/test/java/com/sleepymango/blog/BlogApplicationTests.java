package com.sleepymango.blog;
import com.sleepymango.blog.entity.*;
import com.sleepymango.blog.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void getArticle(){
        User user = userRepository.findById(1L).orElse(null);
        Category category = categoryRepository.findById(1L).orElse(null);
        List<Label> list = labelRepository.findAll();
        Article article = new Article();
        article.setId(null);
        article.setTitle("test");
        article.setContent("test");
        article.setBanner(null);
        article.setView(0L);
        article.setComment(0L);
        article.setLike(0L);
        article.setPublish(new Date());
        article.setStatus("1");
        article.setAuthor(user);
        article.setCategory(category);
        article.setLabels(list);
        article.setComments(null);
        articleRepository.save(article);
    }

    @Test
    @Transactional
    @Rollback(false)
    void getMenu() {
        List<Menu> list = menuRepository.findAllByParentNull();
        for (Menu menu : list) {
            System.out.println(menu);
        }
        Menu menu = new Menu();
//        menu.setMenuId(null);
//        menu.setName("菜单管理");
//        menu.setUrl(null);
//        menu.setStatus(1);
//        menu.setParent(null);
//        menu.setChildren(new ArrayList());
//        Long menuId = menuRepository.save(menu).getMenuId();
//        System.out.println(menuId);

//        Menu parent = menuRepository.findById(10L).orElse(null);
//        Menu menu = new Menu();
//        menu.setMenuId(null);
//        menu.setName("菜单列表");
//        menu.setUrl("/menu/list");
//        menu.setStatus(1);
//        menu.setParent(parent);
//        menu.setChildren(new ArrayList<>());
//        menuRepository.save(menu);
    }

    @Test
    void getArticleAndUser() {
//        Article article = articleDao.findById(1L).orElse(null);
//        System.out.println(article);
    }

    @Test
    void addArticle() {
//        User user = userDao.findById(1L).orElse(null);
//        Article article = new Article();
//        article.setArticleId(0L);
//        article.setArticleTitle("java");
//        article.setArticleContent("hello world");
//        article.setArticleBanner("");
//        article.setArticleTag("");
//        article.setArticleView(0L);
//        article.setArticleComment(0L);
//        article.setArticleLike(0L);
//        article.setArticlePublish(new Date());
//        article.setArticleStatus("");
//        article.setUser(user);
//        article.setCategoryId(0L);
//        articleDao.save(article);
    }

}
