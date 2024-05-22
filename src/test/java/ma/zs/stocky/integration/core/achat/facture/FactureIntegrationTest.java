package ma.zs.stocky.integration.core.achat.facture;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class FactureIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("FactureHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
