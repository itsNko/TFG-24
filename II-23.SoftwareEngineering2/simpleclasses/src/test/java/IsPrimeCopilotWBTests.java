import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exceptions.InvertException;
import exceptions.NoPositiveNumberException;
import functions.Functions;

public class IsPrimeCopilotWBTests {

    @Test
    public void testIsLeapYear() {
        Assertions.assertTrue(Functions.isLeapYear(2020));
        Assertions.assertFalse(Functions.isLeapYear(2021));
        Assertions.assertFalse(Functions.isLeapYear(1900));
        Assertions.assertTrue(Functions.isLeapYear(2000));
    }

    @Test
    public void testGetMonthDays() {
        Assertions.assertEquals(31, Functions.getMonthDays(0)); // January
        Assertions.assertEquals(28, Functions.getMonthDays(1)); // February
        Assertions.assertEquals(30, Functions.getMonthDays(3)); // April
        Assertions.assertEquals(30, Functions.getMonthDays(5)); // June
        Assertions.assertEquals(30, Functions.getMonthDays(8)); // September
        Assertions.assertEquals(30, Functions.getMonthDays(10)); // November
    }

    @Test
    public void testCalculateMonthDays() {
        Assertions.assertEquals(31, Functions.calculateMonthDays(2022, 0)); // January
        Assertions.assertEquals(29, Functions.calculateMonthDays(2020, 1)); // February (leap year)
        Assertions.assertEquals(28, Functions.calculateMonthDays(2021, 1)); // February (non-leap year)
        Assertions.assertEquals(30, Functions.calculateMonthDays(2022, 3)); // April
        Assertions.assertEquals(30, Functions.calculateMonthDays(2022, 5)); // June
        Assertions.assertEquals(30, Functions.calculateMonthDays(2022, 8)); // September
        Assertions.assertEquals(30, Functions.calculateMonthDays(2022, 10)); // November
    }

    @Test
    public void testIsPrime() throws Exception {
        String[] args1 = { "2" };
        String[] args2 = { "5" };
        String[] args3 = { "10" };
        String[] args4 = { "-7" };
        String[] args5 = { "abc" };

        Assertions.assertTrue(Functions.isPrime(args1));
        Assertions.assertTrue(Functions.isPrime(args2));
        Assertions.assertFalse(Functions.isPrime(args3));
        Assertions.assertThrows(NoPositiveNumberException.class, () -> Functions.isPrime(args4));
        Assertions.assertThrows(NoPositiveNumberException.class, () -> Functions.isPrime(args5));
    }

    @Test
    public void testInvert() throws Exception {
        Assertions.assertEquals(321, Functions.invert("123"));
        Assertions.assertEquals(987654321, Functions.invert("123456789"));
        Assertions.assertThrows(InvertException.class, () -> Functions.invert("0"));
        Assertions.assertThrows(InvertException.class, () -> Functions.invert("-123"));
        Assertions.assertThrows(InvertException.class, () -> Functions.invert("12345678901"));
    }
}