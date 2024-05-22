package ma.zs.stocky.integration.core.collaborateur.collaborateur;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class CollaborateurIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("CollaborateurHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
