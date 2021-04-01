package com.sleepymango.blog;

import com.sleepymango.blog.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
//        User user = userRepository.findById(1L).orElse(null);
//        Category category = categoryRepository.findById(1L).orElse(null);
//        List<Label> list = labelRepository.findAll();
//        Article article = new Article();
//        article.setId(null);
//        article.setTitle("test");
//        article.setContent("test");
//        article.setBanner(null);
//        article.setView(0L);
//        article.setComment(0L);
//        article.setLike(0L);
//        article.setPublish(new Date());
//        article.setStatus("1");
//        article.setAuthor(user);
//        article.setCategory(category);
//        article.setLabel(list);
//        article.setComments(null);
//        articleRepository.save(article);
    }

    @Test
    @Transactional
    @Rollback(false)
    void getMenu() {
            menuRepository.deleteById(42L);
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
