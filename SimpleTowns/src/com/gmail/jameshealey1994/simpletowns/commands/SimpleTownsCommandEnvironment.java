package com.gmail.jameshealey1994.simpletowns.commands;

import com.gmail.jameshealey1994.simpletowns.commands.command.SimpleTownsCommand;

/**
 * Represents a plugin command environment (set of plugin commands).
 *
 * @author Saul Johnson
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class SimpleTownsCommandEnvironment {

    /**
     * Commands belonging to the plugin.
     * Listed in priority order (earlier items in the array have a higher
     * priority, when it comes to aliases for example).
     */
    protected SimpleTownsCommand[] commands;

    /**
     * Abstract constructor used to initialise a plugin command environment
     * (set of plugin commands).
     *
     * @param commands  the set of commands usable from this environment
     */
    public SimpleTownsCommandEnvironment(SimpleTownsCommand[] commands) {
        this.commands = commands;
    }

    /**
     * Returns commands belonging to the plugin.
     *
     * @return  commands belonging to the plugin
     */
    public SimpleTownsCommand[] getCommands() {
        return commands;
    }
}