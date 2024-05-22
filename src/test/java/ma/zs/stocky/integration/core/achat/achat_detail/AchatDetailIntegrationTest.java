package ma.zs.stocky.integration.core.achat.achat-detail;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class AchatDetailIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("AchatDetailHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
