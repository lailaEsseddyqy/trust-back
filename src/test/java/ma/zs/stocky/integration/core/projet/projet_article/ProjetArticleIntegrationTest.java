package ma.zs.stocky.integration.core.projet.projet-article;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ProjetArticleIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ProjetArticleHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
