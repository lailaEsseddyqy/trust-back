package ma.zs.stocky.integration.core.achat.etat-achat;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EtatAchatIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EtatAchatHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
