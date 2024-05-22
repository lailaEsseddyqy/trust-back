package ma.zs.stocky;


import ma.zs.stocky.service.impl.admin.article.ArticleAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArticleQuantityChecker {

    @Autowired
    private ArticleAdminServiceImpl articleService;

    // Vérifie les quantités toutes les heures
    @Scheduled(fixedRate = 3600000)
    public void checkQuantities() {
        articleService.checkArticleQuantities();
    }
}

