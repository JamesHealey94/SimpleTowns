package com.gmail.jameshealey1994.simpletowns.commands;

import com.gmail.jameshealey1994.simpletowns.commands.command.STCommand;

/**
 * Represents a plugin command environment (set of plugin commands).
 *
 * @author Saul Johnson
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public abstract class STCommandEnvironment {

    /**
     * Commands belonging to the plugin.
     * Listed in priority order (earlier items in the array have a higher
     * priority, when it comes to aliases for example).
     */
    protected STCommand[] commands;

    /**
     * Abstract constructor used to initialise a plugin command environment
     * (set of plugin commands).
     *
     * @param commands  the set of commands usable from this environment
     */
    public STCommandEnvironment(STCommand[] commands) {
        this.commands = commands;
    }

    /**
     * Returns commands belonging to the plugin.
     *
     * @return  commands belonging to the plugin
     */
    public STCommand[] getCommands() {
        return commands;
    }
}