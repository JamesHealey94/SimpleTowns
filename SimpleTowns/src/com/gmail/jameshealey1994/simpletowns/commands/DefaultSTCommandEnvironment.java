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
     * Initialises a new instance of the default command environment.
     */
    public DefaultSTCommandEnvironment() {
        super(new STCommand[] {
            new CreateCommand(),
            new ClaimCommand(),
            new UnclaimCommand(),
            new AddCommand(),
            new RemoveCommand(),
            new PromoteCommand(),
            new DemoteCommand(),
            new RenameCommand(),
            new ListCommand(),
            new InfoCommand(),
            new ChunksCommand(),
            new DeleteCommand(),
            new HelpCommand(),
            new ReloadCommand(),
            new LogCommand(),
            new DebugCommand()
        });
    }
}