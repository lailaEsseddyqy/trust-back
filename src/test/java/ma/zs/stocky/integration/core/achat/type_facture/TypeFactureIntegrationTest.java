package ma.zs.stocky.integration.core.achat.type-facture;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TypeFactureIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TypeFactureHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
