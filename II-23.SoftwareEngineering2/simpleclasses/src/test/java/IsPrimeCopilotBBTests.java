import org.junit.jupiter.api.Test;

import exceptions.MissingArgumentException;
import exceptions.NoPositiveNumberException;
import exceptions.Only1ArgumentException;
import functions.Functions;

import static org.junit.jupiter.api.Assertions.*;

class IsPrimeCopilotBBTests {

    @Test
    void testIsPrimeWithPrimeNumber() {
        String[] args = { "7" };
        try {
            assertTrue(Functions.isPrime(args));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testIsPrimeWithNonPrimeNumber() {
        String[] args = { "10" };
        try {
            assertFalse(Functions.isPrime(args));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testIsPrimeWithNegativeNumber() {
        String[] args = { "-5" };
        assertThrows(NoPositiveNumberException.class, () -> Functions.isPrime(args));
    }

    @Test
    void testIsPrimeWithZero() {
        String[] args = { "0" };
        assertThrows(NoPositiveNumberException.class, () -> Functions.isPrime(args));
    }

    @Test
    void testIsPrimeWithInvalidInput() {
        String[] args = { "abc" };
        assertThrows(NoPositiveNumberException.class, () -> Functions.isPrime(args));
    }

    @Test
    void testIsPrimeWithMissingArgument() {
        String[] args = null;
        assertThrows(MissingArgumentException.class, () -> Functions.isPrime(args));
    }

    @Test
    void testIsPrimeWithExtraArgument() {
        String[] args = { "5", "10" };
        assertThrows(Only1ArgumentException.class, () -> Functions.isPrime(args));
    }
}