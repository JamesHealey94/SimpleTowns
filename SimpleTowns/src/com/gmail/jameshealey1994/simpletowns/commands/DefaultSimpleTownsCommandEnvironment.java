package com.gmail.jameshealey1994.simpletowns.commands;

import com.gmail.jameshealey1994.simpletowns.commands.command.CreateCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.DebugCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.HelpCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.InfoCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.ListCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.ReloadCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.SimpleTownsCommand;

/**
 * Represents the default command environment for the SimpleTowns plugin.
 *
 * @author Saul Johnson
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class DefaultSimpleTownsCommandEnvironment extends SimpleTownsCommandEnvironment {

    /**
     * Initialises a new instance of the default command environment for the
     * SimpleTowns plugin.
     */
    public DefaultSimpleTownsCommandEnvironment() {
        super(new SimpleTownsCommand[] {
            new HelpCommand(),
            new ReloadCommand(),
            new DebugCommand(),
            new CreateCommand(),
            new ListCommand(),
            new InfoCommand()
        });
    }
}