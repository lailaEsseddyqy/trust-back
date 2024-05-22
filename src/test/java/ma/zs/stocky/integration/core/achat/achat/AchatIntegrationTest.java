package ma.zs.stocky.integration.core.achat.achat;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class AchatIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("AchatHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
