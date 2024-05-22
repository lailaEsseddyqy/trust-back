package ma.zs.stocky.integration.core.commun.fournisseur;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class FournisseurIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("FournisseurHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
