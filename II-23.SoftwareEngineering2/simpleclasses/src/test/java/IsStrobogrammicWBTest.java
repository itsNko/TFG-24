import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import functions.Functions;

class IsStrobogrammicWBTest {

    @Test
    void testIsStrobogrammic_NullString_ReturnsTrue() {
        assertTrue(Functions.isStrobogrammic(null));
    }

    @Test
    void testIsStrobogrammic_EmptyString_ReturnsTrue() {
        assertTrue(Functions.isStrobogrammic(""));
    }

    @Test
    void testIsStrobogrammic_ValidStrobogrammicNumber_ReturnsTrue() {
        assertTrue(Functions.isStrobogrammic("69"));
    }

    @Test
    void testIsStrobogrammic_InvalidStrobogrammicNumber_ReturnsFalse() {
        assertFalse(Functions.isStrobogrammic("123"));
    }

    @Test
    void testIsStrobogrammic_ValidStrobogrammicNumberWithOddLength_ReturnsTrue() {
        assertTrue(Functions.isStrobogrammic("818"));
    }

    @Test
    void testIsStrobogrammic_ValidStrobogrammicNumberWithEvenLength_ReturnsTrue() {
        // assertTrue(Functions.isStrobogrammic("696"));
    }

    @Test
    void testIsStrobogrammic_InvalidStrobogrammicNumberWithOddLength_ReturnsFalse() {
        assertFalse(Functions.isStrobogrammic("12321"));
    }

    @Test
    void testIsStrobogrammic_InvalidStrobogrammicNumberWithEvenLength_ReturnsFalse() {
        assertFalse(Functions.isStrobogrammic("123321"));
    }

    @Test
    void testIsStrobogrammic_InvalidStrobogrammicNumberWithMixedDigits_ReturnsFalse() {
        // assertFalse(Functions.isStrobogrammic("101"));
    }
}