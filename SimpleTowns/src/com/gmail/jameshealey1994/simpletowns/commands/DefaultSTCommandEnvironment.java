package com.gmail.jameshealey1994.simpletowns.commands;

import com.gmail.jameshealey1994.simpletowns.commands.command.*;

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
            new DeleteCommand(),
            new ClaimCommand(),
            new UnclaimCommand(),
            new AddCommand(),
            new RemoveCommand(),
            new PromoteCommand(),
            new DemoteCommand(),
            new ListCommand(),
            new InfoCommand(),
            new LogCommand()
        });
    }
}