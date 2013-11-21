package com.gmail.jameshealey1994.simpletowns.localisation;

/**
 * Enum to represent a single entry in a Localisation.
 * Holds the config key, the usage, and the default value.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public enum LocalisationEntry {

    /**
     * Message telling player they do not have permission to use a command.
     */
    ERR_PERMISSION_DENIED("MsgPermissionDenied", null, "&cPermission denied"),

    /**
     * Message telling user the command can only be used by players.
     */
    ERR_PLAYER_ONLY_COMMAND("MsgPlayerOnlyCommand", null, "&cOnly players can use this command"),

    /**
     * Message telling user to specify a valid status ('true' or 'false').
     */
    ERR_SPECIFY_STATUS("MsgSpecifyPVPStatus", null, "&cPlease specify a status ('true' or 'false')"),

    /**
     * Message telling user that the player specified cannot be found.
     * %1$s - invalid player name
     */
    ERR_PLAYER_NOT_FOUND ("MsgPlayerNotFound", "%1$s - invalid player name", "&cPlayer is not online or is invalid: '%1$s'"),

    /**
     * Message telling user that the world specified cannot be found.
     * %1$s - invalid world name
     */
    ERR_WORLD_NOT_FOUND ("MsgWorldNotFound", "%1$s - invalid world name", "&cWorld not found: '%1$s'"),

    /**
     * Message telling user that they need to specify a world.
     */
    ERR_SPECIFY_WORLD ("MsgSpecifyWorld", null, "&cPlease specify a world"),

    /**
     * Message telling user that they need to specify a player.
     */
    ERR_SPECIFY_PLAYER ("MsgSpecifyPlayer", null, "&cPlease specify a player"),

    /**
     * Message telling user that they have sent too many arguments
     * for a command.
     */
    ERR_TOO_MANY_ARGUMENTS ("MsgTooManyArguments", null, "&cToo many arguments"),

    /**
     * Message telling player they attacked another player, and how much damage
     * they inflicted on that player.
     * Only shown when in debug mode.
     *
     * %1$s - attacked player's name
     * %2$s - damage inflicted
     */
    DEBUG_ATTACKED_PLAYER ("DebugAttackedPlayer", "%1$s - attacked player's name\n# %2$s - damage inflicted", "&7Attacked %1$s for %2$s damage"),

    /**
     * Message telling player they have been attacked by another player, and how
     * much damage they have been inflicted by that player.
     * Only shown when in debug mode.
     *
     * %1$s - attacker's name
     * %2$s - damage inflicted
     */
    DEBUG_ATTACKED_BY_PLAYER ("DebugAttackedByPlayer", "%1$s - attacker's name\n# %2$s - damage inflicted", "&7Attacked by %1$s for %2$s damage"),

    /**
     * Message logged to file when a new town is created.
     * %1$s - town name
     * %2$s - player name
     */
    LOG_TOWN_CREATED ("LogTownCreated", "%1$s - town name\n# %2$s - player name", "New town '%1$s' created by '%2$s'"),

    /**
     * Message logged to file when a new leader is added to a town.
     * %1$s - town name
     * %2$s - leader name
     * %3$s - player name
     */
    LOG_TOWN_LEADER_ADDED ("LogTownLeaderAdded", "%1$s - town name\n# %2$s - leader name\n# %3$s - player name", "Player '%1$s' added as town leader to '%2$s' by '%3$s'"),

    /**
     * Message logged to file when a new chunk is claimed.
     * %1$s - town name
     * %2$s - player name
     * %3$s - world name
     * %4$s - chunk X
     * %5$s - chunk Z
     */
    LOG_CHUNK_CLAIMED ("LogChunkClaimed", "%1$s - town name\n# %2$s - player name\n# %3$s - world name\n# %4$s - chunk X\n# %5$s - chunk Z", "Chunk (%4$s, %5$s) in world %3$s claimed for town '%1$s' by player '%2$s'"),

    /**
     * Message telling player that a specified town already exists.
     * %1$s - town name
     */
    MSG_TOWN_ALREADY_EXISTS ("MsgTownAlreadyExists", "%1$s - town name", "&cTown named '%1$s' already exists!"),

    /**
     * Message telling player that a chunk they tried to claim already belongs
     * to a town.
     * %1$s - town name
     */
    MSG_CHUNK_ALREADY_CLAIMED ("MsgChunkAlreadyClaimed", "%1$s - town name", "&cChunk already claimed by town '%1$s'"),

    /**
     * Message to confirm to sender that a town has been created.
     * %1$s - town name
     */
    MSG_TOWN_CREATED ("MsgTownCreated", "%1$s - town name", "&6Congratulations, '%1$s' created!"),

    /**
     * Message to be broadcast to server telling players that a town has been
     * created.
     * %1$s - town name
     */
    MSG_TOWN_CREATED_BROADCAST ("MsgTownCreatedBroadcast", "%1$s - town name", "&6New town '%1$s' created!"),

    /**
     * Message telling user the configuration has been reloaded.
     */
    MSG_CONFIG_RELOADED ("MsgConfigReloaded", null, "&7Configuration reloaded."),

    /**
     * Message telling user the debug status has been set to a new value.
     * %1$s - debug status
     */
    MSG_DEBUG_SET_TO_STATUS ("MsgDebugSetToSTATUS", "%1$s - debug status", "&7Debug status set to '%1$s'"),

    /**
     * Description for Help command.
     */
    DESCRIPTION_HELP ("DescHelp", null, "Shows help menu"),

    /**
     * Description for Info command.
     */
    DESCRIPTION_INFO ("DescInfo", null, "Displays default PVP status of server and worlds"),

    /**
     * Description for Reload command.
     */
    DESCRIPTION_RELOAD ("DescReload", null, "Reloads config values"),

    /**
     * Description for Debug command.
     */
    DESCRIPTION_DEBUG ("DescDebug", null, "Changes debug status"),

    /**
     * Description for Set command.
     */
    DESCRIPTION_SET ("DescSet", null, "Changes PVP status for [username] in [world] to <on / off>"),

    /**
     * Description for Set Server Default command.
     */
    DESCRIPTION_SET_SERVER_DEFAULT ("DescSetServerDefault", null, "Sets default PVP status for server to <on / off>"),

    /**
     * Description for Set World Default command.
     */
    DESCRIPTION_SET_WORLD_DEFAULT ("DescSetWorldDefault", null, "Sets default PVP status for [world] to <on / off>"),

    /**
     * Description for Status command.
     */
    DESCRIPTION_STATUS ("DescStatus", null, "Displays PVP status of [username] in [world]"),

    /**
     * Displayed at the top of the Info command.
     */
    INFO_HEADER ("InfoHeader", null, "&7------ Default PVP Status ------"),

    /**
     * The server's default PVP status.
     * %1$s - server's default PVP status
     */
    INFO_SERVER ("InfoServer", "%1$s - server's default PVP status", "&dServer: %1$s"),

    /**
     * A world and it's default PVP status.
     * %1$s - world name
     * %2$s - world's default PVP status
     */
    INFO_WORLD ("InfoWorld", "%1$s - world name\n# %2$s - world's default PVP status", "&d%1$s: %2$s"),

    /**
     * Displayed at the top of the Help command.
     * %1$s - plugin description
     */
    HELP_HEADER ("HelpHeader", "%1$s - plugin description", "&7------ %1$s ------"),

    /**
     * Displayed for each command in the help command.
     * %1$s - command name
     * %2$s - command description
     */
    HELP_ENTRY ("HelpSeparator", "%1$s - command name\n# %2$s - command description", "%1$s: &7%2$s");

    /**
     * The name of the entry, as found in the localisation file.
     */
    private String name;

    /**
     * The usage for the entry, used as a comment in the localisation file to
     * help the user.
     * An empty string should be ignored when writing to file.
     */
    private String usage;

    /**
     * The default value of the entry, as found in the localisation file.
     */
    private String defaultValue;

    /**
     * Constructor.
     *
     * @param name          name of the entry, as found in the localisation file
     * @param usage         usage for the entry, used as a comment in the
     *                      localisation file to help the user, can be empty
     * @param defaultValue  default value of the entry, as found in the
     *                      localisation file
     */
    LocalisationEntry(String name, String usage, String defaultValue) {
        this.name = name;
        this.usage = usage;
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the name of the LocalistionEntry.
     *
     * @return  name of the LocalistionEntry
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the usage of the LocalistionEntry.
     *
     * @return  usage of the LocalistionEntry, empty string for no usage.
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Returns the default value of the LocalisationEntry.
     *
     * @return  default value of the LocalisationEntry
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        if (getUsage() == null) {
            return "\n" + getName() + ": '" + getDefaultValue().replace("'", "''") + "'\n";
        } else {
            return "\n# " + getUsage() + "\n" + getName() + ": '" + getDefaultValue().replace("'", "''") + "'\n";
        }
    }
}