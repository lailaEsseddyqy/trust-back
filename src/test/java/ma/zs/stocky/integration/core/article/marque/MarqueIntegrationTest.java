package ma.zs.stocky.integration.core.article.marque;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class MarqueIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("MarqueHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
