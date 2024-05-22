package ma.zs.stocky.integration.core.projet.projet-collaborateur;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ProjetCollaborateurIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ProjetCollaborateurHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
