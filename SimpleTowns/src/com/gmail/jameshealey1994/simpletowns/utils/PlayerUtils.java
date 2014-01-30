package com.gmail.jameshealey1994.simpletowns.utils;

import java.util.regex.Pattern;

/**
 * Utility class for methods relating to Players.
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class PlayerUtils {

    /**
     * Returns if a string is a valid name.
     * Valid names are between 1 and 16 characters long, and contain
     * only letters, numbers, and underscores.
     *
     * @param possibleName      possible name to be checked for validity
     * @return                  if the town name is valid
     */
    public static boolean isValidName(String possibleName) {
        if (1 <= possibleName.length() && possibleName.length() <= 16) {
            // http://stackoverflow.com/a/8248352/1735524
            final Pattern p = Pattern.compile("[^a-zA-Z0-9_]");
            final boolean hasSpecialChar = p.matcher(possibleName).find();
            return !hasSpecialChar;
        }
        return false;
    }
}