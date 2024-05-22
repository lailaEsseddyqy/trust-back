package ma.zs.stocky.integration.core.article.categorie-article;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class CategorieArticleIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("CategorieArticleHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
