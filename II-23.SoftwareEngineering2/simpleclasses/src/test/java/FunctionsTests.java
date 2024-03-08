import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import exceptions.InvertException;
import exceptions.MissingArgumentException;
import exceptions.NoPositiveNumberException;
import exceptions.Only1ArgumentException;
import functions.Functions;

class FunctionsTests {
    int year, month;
    String args[];

    ////////////////////////
    // calculateMonthDays //
    ////////////////////////

    @Test
    @DisplayName("calculateMonthDays Function Dynamic White Box Condition Unit Test: 1st Case")
    void calculateMonthDaysDWCTest1() {
        year = 2003;
        month = 0; // January
        assertEquals(31, Functions.calculateMonthDays(year, month));
    }

    @Test
    @DisplayName("calculateMonthDays Function Dynamic White Box Condition Unit Test: 2nd Case")
    void calculateMonthDaysDWCTest2() {
        year = 2004;
        month = 1; // February
        assertEquals(29, Functions.calculateMonthDays(year, month));
    }

    @Test
    @DisplayName("calculateMonthDays Function Dynamic White Box Condition Decision Unit Test: 1st Case")
    void calculateMonthDaysDWCDTest1() {
        year = 2003;
        month = 0; // January
        assertEquals(31, Functions.calculateMonthDays(year, month));
    }

    @Test
    @DisplayName("calculateMonthDays Function Dynamic White Box Condition Decision Unit Test: 3rd Case")
    void calculateMonthDaysDWCDTest3() {
        year = 2004;
        month = 0; // January
        assertEquals(31, Functions.calculateMonthDays(year, month));
    }

    /////////////
    // isPrime //
    /////////////

    @Test
    @DisplayName("calculateMonthDays Function Dynamic White Box Condition Unit Test: 1st Case")
    void isPrimeDWCTest1() {
        args = null;
        assertThrows(MissingArgumentException.class, () -> Functions.isPrime(args));
    }

    @Test
    @DisplayName("calculateMonthDays Function Dynamic White Box Condition Unit Test: 2nd Case")
    void isPrimeDWCTest2() {
        args = new String[] {"4", "12"};
        assertThrows(Only1ArgumentException.class, () -> Functions.isPrime(args));
    }

    @Test
    @DisplayName("calculateMonthDays Function Dynamic White Box Condition Unit Test: 3rd Case")
    void isPrimeDWCTest3() {
        args = new String[] {"-4"};
        assertThrows(NoPositiveNumberException.class, () -> Functions.isPrime(args));
    }

    @ParameterizedTest
    @DisplayName("calculateMonthDays Function Dynamic White Box Condition Unit Test: 4-7 Cases")
    @MethodSource("params")
    void isPrimeDWCParamsTest(String[] args) {
        try {
            assertTrue(Functions.isPrime(args));
        } catch (Exception e) {
            fail();
        }
    }

    private static Collection<Arguments> params() {
        return List.of(Arguments.of(new String[] {"2"}, new String[] {"3"}, new String[] {"4"}, new String[] {"17"}));
    }

    @Test
    @DisplayName("calculateMonthDays Function Dynamic White Box Condition Unit Test: 8th Case")
    void isPrimeDWCTest8() {
        args = new String[] {"NaN."};
        assertThrows(NoPositiveNumberException.class, () -> Functions.isPrime(args));
    }

    ////////////
    // invert //
    ////////////

    @Test
    @DisplayName("invert Function Dynamic Black Box Equivalence Partition and Limit Value Unit Test: 1st Case")
    void invertDBPVTest1() {
        try {
            int num = Functions.invert("123456789");
            assertEquals(987654321, num);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("invert Function Dynamic Black Box Equivalence Partition and Limit Value Unit Test: 2nd Case")
    void invertDBPVTest2() {
        assertThrows(InvertException.class, () -> Functions.invert(null));
    }

    @Test
    @DisplayName("invert Function Dynamic Black Box Equivalence Partition and Limit Value Unit Test: 3rd Case")
    void invertDBPVTest3() {
        assertThrows(InvertException.class, () -> Functions.invert("a"));
    }

    @Test
    @DisplayName("invert Function Dynamic Black Box Equivalence Partition and Limit Value Unit Test: 4th Case")
    void invertDBPVTest4() {
        assertThrows(InvertException.class, () -> Functions.invert("9"));
    }

    @Test
    @DisplayName("invert Function Dynamic Black Box Equivalence Partition and Limit Value Unit Test: 5th Case")
    void invertDBPVTest5() {
        assertThrows(InvertException.class, () -> Functions.invert("1e10"));
    }
}
