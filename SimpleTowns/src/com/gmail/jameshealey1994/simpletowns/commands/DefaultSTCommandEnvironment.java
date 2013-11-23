package com.gmail.jameshealey1994.simpletowns.commands;

import com.gmail.jameshealey1994.simpletowns.commands.command.ClaimCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.CreateCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.DebugCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.HelpCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.InfoCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.ListCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.LogCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.ReloadCommand;
import com.gmail.jameshealey1994.simpletowns.commands.command.STCommand;

/**
 * Represents the default command environment for the SimpleTowns plugin.
 *
 * @author Saul Johnson
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class DefaultSTCommandEnvironment extends STCommandEnvironment {

    /**
     * Initialises a new instance of the default command environment for the
     * SimpleTowns plugin.
     */
    public DefaultSTCommandEnvironment() {
        super(new STCommand[] {
            new HelpCommand(),
            new ReloadCommand(),
            new DebugCommand(),
            new CreateCommand(),
            new ClaimCommand(),
            new ListCommand(),
            new InfoCommand(),
            new LogCommand()
        });
    }
}