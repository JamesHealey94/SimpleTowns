package com.gmail.jameshealey1994.simpletowns.utils;

import java.util.regex.Pattern;

/**
 * Checks names for validity.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class NameValidityChecker {

    /**
     * Minimum length of a name.
     */
    public static final int MIN_LENGTH = 1;

    /**
     * Maximum length of a name.
     */
    public static final int MAX_LENGTH = 16;

    /**
     * Possible name, to be checked for validity.
     */
    private final String possibleName;

    /**
     * Constructor - sets possibleName.
     *
     * @param possibleName  possible name, to be checked for validity
     */
    public NameValidityChecker(final String possibleName) {
        this.possibleName = possibleName;
    }

    /**
     * Returns if possibleName is a valid name.
     * Currently, valid names are between 1 and 16 characters long,
     * and contain only letters, numbers, and underscores.
     *
     * @return  if the town name is valid
     */
    public boolean isValidName() {
        return isAcceptableLength() && !containsSpecialChar();
    }

    /**
     * Returns if possibleName is an acceptable length.
     *
     * @return  if possibleName is an acceptable length
     */
    private boolean isAcceptableLength() {
        return MIN_LENGTH <= possibleName.length() && possibleName.length() <= MAX_LENGTH;
    }

    /**
     * Returns if possibleName contains a special character.
     *
     * @return  if possibleName contains a special character
     */
    private boolean containsSpecialChar() {
        // http://stackoverflow.com/a/8248352/1735524
        final Pattern p = Pattern.compile("[^a-zA-Z0-9_]");
        return p.matcher(possibleName).find();
    }
}