package ma.zs.stocky.integration.core.article.article;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ArticleIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ArticleHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
