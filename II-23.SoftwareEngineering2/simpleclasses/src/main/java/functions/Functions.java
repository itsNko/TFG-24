package functions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import exceptions.InvertException;
import exceptions.MissingArgumentException;
import exceptions.NoPositiveNumberException;
import exceptions.Only1ArgumentException;

public class Functions {

    /**
     * @param year The year to know whether is leap year.
     * @return True if the year given is a leap year.
     */
    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

    /**
     * @param month the month to get the amount of days of (Month 0 is January).
     * @return Returns the number of days in a month for a non leap year.
     */
    public static int getMonthDays(int month) {
        switch (month) {
            case 3: // April
            case 5: // June
            case 8: // Septemeber
            case 10: // November
                return 30;
            case 1: // February
                return 28;
            default:
                return 31;
        }
    }

    /**
     * @param year  The year to get the amount of days of.
     * @param month the month to get the amount of days of (Month 0 is January).
     * @return The amount of days in a given month of a year. Takes into account
     *         leap years.
     */
    public static int calculateMonthDays(int year, int month) {
        if (isLeapYear(year) && (month == 1))
            return 29;
        else
            return getMonthDays(month);
    }

    /**
     * @param args Set of strings consisting of integers.
     * @return Returns whether the numbers in args are all prime or not.
     * @throws MissingArgumentException  When there are no numbers in args.
     * @throws Only1ArgumentException    When there is more than 1 number in the
     *                                   args variable.
     * @throws NoPositiveNumberException When there are negative numbers in the args
     *                                   variable.
     */
    public static boolean isPrime(String[] args)
            throws MissingArgumentException, Only1ArgumentException, NoPositiveNumberException {
        if (args == null)
            throw new MissingArgumentException();
        else if (args.length > 1)
            throw new Only1ArgumentException();
        else {
            try {
                float numF = Float.parseFloat(args[0]);
                int num = (int) numF;
                if (num <= 0)
                    throw new NoPositiveNumberException();
                else {
                    for (int i = 2; i < num; i++)
                        if (num % i == 0)
                            return false;
                    return true;
                }
            } catch (NumberFormatException e) {
                throw new NoPositiveNumberException();
            }
        }
    }

    /**
     * @param num The number to invert.
     * @return The given number inverted.
     * @throws InvertException Thrown when the number is not a positive integer
     *                         between 2 and 9
     *                         digits (included).
     */
    public static int invert(String num) throws InvertException {
        int result = 0;
        try {
            int number = Integer.parseInt(num);
            if (number < 1 || number < 10 || number > 1e10)
                throw new InvertException();

            int remainder;
            while (number > 0) {
                remainder = number % 10;
                result = result * 10 + remainder;
                number /= 10;
            }
        } catch (Exception e) {
            throw new InvertException();
        }
        return result;
    }

    /**
     * @param number The number to check if it is strobogrammic.
     *               A strobogrammatic number is a number whose numeral is
     *               rotationally symmetric, so that it appears the same when
     *               rotated 180 degrees. In other words, the numeral looks the same
     *               right-side up and upside down (e.g., 69, 96, 1001). A
     *               strobogrammatic prime is a strobogrammatic number that is also
     *               a prime number, i.e., a number that is only divisible by one
     *               and itself (e.g., 11). It is a type of ambigram, words and
     *               numbers that retain their meaning when viewed from a different
     *               perspective, such as palindromes.
     *               https://www.w3resource.com/java-exercises/basic/java-basic-exercise-186.php
     * @return True if the number is strobogrammic.
     */
    public static boolean isStrobogrammic(String number) {
        // Check for null or empty string
        if (number == null || number.length() == 0) {
            return true;
        }
        // Create a HashMap to store Strobogrammatic pairs
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        // Use two pointers to traverse the string from both ends
        int left = 0;
        int right = number.length() - 1;
        // Continue until the left pointer is less than or equal to the right pointer
        while (left <= right) {
            // Check if the characters at the current positions are valid Strobogrammatic
            // pairs
            if (!map.containsKey(number.charAt(right)) || number.charAt(left) != map.get(number.charAt(right))) {
                return false;
            }
            // Move the pointers towards the center
            left++;
            right--;
        }
        // If the loop completes, the string is Strobogrammatic
        return true;
    }
}
