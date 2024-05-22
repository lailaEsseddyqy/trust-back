package ma.zs.stocky.integration.core.collaborateur.domaine-intervention;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DomaineInterventionIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DomaineInterventionHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
