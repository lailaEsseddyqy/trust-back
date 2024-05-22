package ma.zs.stocky.integration.core.commun.client;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class ClientIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("ClientHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
