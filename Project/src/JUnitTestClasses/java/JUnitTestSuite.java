import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        // Test Class for Main.Deck
        JUnitDeckTest.class,
        // Test Class for Main.Pile
        JUnitPileTest.class
})

public class JUnitTestSuite {
}