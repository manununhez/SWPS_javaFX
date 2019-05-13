package pl.swps.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling dates.
 *
 * @author Marco Jakob
 */
public class DateUtil {

    /**
     * The date pattern that is used for conversion. Change as you wish.
     */
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm";

    /**
     * The date formatter.
     */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    /**
     * Returns the given date as a well formatted String. The above defined
     * {@link DateUtil#DATE_PATTERN} is used.
     *
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }


    public static String format(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return DATE_TIME_FORMATTER.format(date);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN}
     * to a {@link LocalDate} object.
     * <p>
     * Returns null if the String could not be converted.
     *
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }


    public static LocalDateTime parseDateTime(String dateString) {
        return LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);

    }

    /**
     * Checks the String whether it is a valid date.
     *
     * @param dateString Birthday
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }
}

