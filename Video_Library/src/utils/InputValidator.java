package utils;

import java.util.regex.Pattern;

/**
 * Input validation utility class
 * Provides validation methods for various input types
 * 
 * @author Palaash Atri
 */
public class InputValidator {
    
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$");
    private static final Pattern ID_PATTERN = Pattern.compile("^[0-9]{4}$");
    
    /**
     * Validate if string is not null and not empty
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
    
    /**
     * Validate string length
     */
    public static boolean isValidLength(String value, int maxLength) {
        return value != null && value.length() <= maxLength;
    }
    
    /**
     * Validate phone number (10 digits)
     */
    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }
    
    /**
     * Validate date format (YYYY/MM/DD)
     */
    public static boolean isValidDate(String date) {
        return date != null && DATE_PATTERN.matcher(date).matches();
    }
    
    /**
     * Validate ID format (4 digits)
     */
    public static boolean isValidId(String id) {
        return id != null && ID_PATTERN.matcher(id).matches();
    }
    
    /**
     * Validate numeric value
     */
    public static boolean isNumeric(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validate positive integer
     */
    public static boolean isPositiveInteger(String value) {
        if (!isNumeric(value)) {
            return false;
        }
        return Integer.parseInt(value) > 0;
    }
    
    /**
     * Get validation error message for field
     */
    public static String getErrorMessage(String fieldName, String value) {
        if (!isNotEmpty(value)) {
            return fieldName + " is required";
        }
        return null;
    }
}
