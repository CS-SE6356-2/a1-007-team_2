import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        // Test Class for Deck
        JUnitDeckTest.class,
        // Test Class for Pile
        JUnitPileTest.class,
        // Test Class for Card
        JUnitCardTest.class
})

public class JUnitTestSuite {
}