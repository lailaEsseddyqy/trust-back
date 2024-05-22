package ma.zs.stocky.integration.core.article.etat-article;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EtatArticleIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EtatArticleHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
