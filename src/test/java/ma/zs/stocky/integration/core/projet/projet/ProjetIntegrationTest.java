package ma.zs.stocky.integration.core.projet.projet;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ProjetIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ProjetHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
