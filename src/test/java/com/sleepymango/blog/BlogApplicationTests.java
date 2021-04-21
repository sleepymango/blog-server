package com.sleepymango.blog;

import com.sleepymango.blog.entity.Label;
import com.sleepymango.blog.repository.*;
import com.sleepymango.blog.service.impl.ArticleServiceImpl;
import com.sleepymango.blog.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private CategoryServiceImpl categoryService;


    @Test
    void cate() {
//        categoryService.test();
    }

    @Test
    void getA() {
        articleService.test();
    }

    @Test
    void deleteByLabel() {
        labelRepository.deleteById(3L);
//        articleRepository.deleteById(2L);
    }

    @Test
    void getLabels() {
        List<Label> all = labelRepository.findAll();
        for (Label label : all) {
            System.out.println(label);
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void getArticle() {
//        User user = userRepository.findById(1L).orElse(null);
//        Category category = categoryRepository.findById(1L).orElse(null);
//        List<Label> list = labelRepository.findAll();
//        Article article = new Article();
//        article.setId(null);
//        article.setTitle("test");
//        article.setContent("test");
//        article.setBanner(null);
//        article.setView(0);
//        article.setComment(0);
//        article.setLike(0);
//        article.setPublish(new Date());
//        article.setStatus(1);
//        article.setAuthorId(user.getId());
//        article.setCategoryId(category.getId());
//        article.setLabels(list);
//        articleRepository.save(article);
    }

    @Test
    @Transactional
    @Rollback(false)
    void getMenu() {

        //
//        Menu parent = menuRepository.findById(13L).orElse(null);
//        Menu children = menuRepository.findById(14L).orElse(null);
//        Menu parent = new Menu();
//        parent.setName("我是父");
//        parent.setUrl("");
//        parent.setParent(new Menu());
//        parent.setChildren(new ArrayList());
//        Menu children = new Menu();
//        children.setName("测试");
//        children.setUrl("/test");
//        ArrayList<Menu> menus = new ArrayList<>();
//        menus.add(children);
//        parent.setChildren(menus);
//        children.setParent(parent);
        menuRepository.deleteById(10L);


//        children.setChildren(new ArrayList());

        // 一   需要设定一和多的关系
//        Menu menu = new Menu();
//        menu.setName("用户管理");
//        // 多  关系维护方值需要设定值才能确认关系  menu1.setParent(menu);
//        Menu menu1 = new Menu();
//        menu1.setName("用户列表");
//
//        ArrayList<Menu> menus = new ArrayList<>();
//        menus.add(menu1);
//
////        menu1.setParent(menu);
//        menu.setChildren(menus);
//        menuRepository.save(menu);
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
