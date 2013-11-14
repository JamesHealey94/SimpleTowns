package com.gmail.jameshealey1994.simpletowns.localisation;

/**
 * Interface to define classes that contain localisations.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public interface Localisable {

    /**
     * Gets the current localisation.
     *
     * @return      the current localisation for the plugin
     */
    Localisation getLocalisation();

    /**
     * Sets the current localisation.
     *
     * @param localisation      the new localisation
     */
    void setLocalisation(Localisation localisation);

}