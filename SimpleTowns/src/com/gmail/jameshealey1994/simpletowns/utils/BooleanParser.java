package com.gmail.jameshealey1994.simpletowns.utils;

/**
 * Class holding variables and methods related to parsing boolean values as
 * a toggle, or positive or negative value.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class BooleanParser {

    /**
     * Strings used to signify a toggle.
     */
    public static final String[] TOGGLE_VALUES = new String[] {"toggle"};

    /**
     * Strings representing a positive value.
     */
    public static final String[] POSITIVE_VALUES = new String[] {"true", "t", "yes", "y", "on"};

    /**
     * Strings representing a negative value.
     */
    public static final String[] NEGATIVE_VALUES = new String[] {"false", "f", "no", "n", "off"};

    /**
     * String being parsed.
     */
    private final String string;

    /**
     * Constructor - Sets String to be parsed.
     *
     * @param string    string being parsed
     */
    public BooleanParser(String string) {
        this.string = string;
    }

    /**
     * Converts a string to a Boolean depending on the contents of the String.
     * Case insensitive.
     * Supports toggling.
     * Can be null.
     *
     * @param current   the current status
     * @return          the opposite of current if the string is in the toggle array
     *                  true        if string is in the positive string array
     *                  false       if string is in the negative string array
     *                  null        if no matches are found
     */
    public Boolean parse(final boolean current) {
        for (String s : TOGGLE_VALUES) {
            if (string.equalsIgnoreCase(s)) {
                return !current;
            }
        }
        for (String s : POSITIVE_VALUES) {
            if (string.equalsIgnoreCase(s)) {
                return true;
            }
        }
        for (String s : NEGATIVE_VALUES) {
            if (string.equalsIgnoreCase(s)) {
                return false;
            }
        }
        return null;
    }
}