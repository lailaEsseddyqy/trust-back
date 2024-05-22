package ma.zs.stocky.integration.core.projet.piece;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class PieceIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("PieceHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
