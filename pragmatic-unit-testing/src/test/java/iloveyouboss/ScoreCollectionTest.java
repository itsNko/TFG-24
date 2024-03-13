package iloveyouboss;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreCollectionTest {
    ScoreCollection collection;

    @BeforeEach
    void beforeEach() {
        collection = new ScoreCollection();
    }

    @Test
    void answersArithmeticMeanOfTwoNumbers() {
        collection.add(() -> 5);
        collection.add(() -> 7);

        int mean = collection.arithmeticMean();
        assertEquals(6, mean);
    }
}
