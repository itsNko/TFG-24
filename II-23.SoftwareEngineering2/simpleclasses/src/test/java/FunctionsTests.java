import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tfg23.Functions;

class FunctionsTests {
    int year, month;

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
}
