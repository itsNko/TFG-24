package functions;

import java.util.Calendar;

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
     * @param year The year to get the amount of days of.
     * @param month the month to get the amount of days of (Month 0 is January).
     * @return The amount of days in a given month of a year. Takes into account leap years.
     */
    public static int calculateMonthDays(int year, int month) {
        if (isLeapYear(year) && (month == 1))
            return 29;
        else
            return getMonthDays(month);
    }

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
}
