package ma.zs.stocky.integration.core.collaborateur.domaine-intervention-collaborateur;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DomaineInterventionCollaborateurIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DomaineInterventionCollaborateurHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
