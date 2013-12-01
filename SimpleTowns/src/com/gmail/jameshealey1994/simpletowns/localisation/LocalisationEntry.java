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
     * Message telling user to specify a valid town.
     */
    ERR_SPECIFY_TOWN ("MsgSpecifyTown", null, "&cPlease specify a town"),

    /**
     * Message telling user to specify a valid town name.
     */
    ERR_SPECIFY_TOWN_NAME ("MsgSpecifyTownName", null, "&cPlease specify a town name"),

    /**
     * Message telling user that town names must be a single word.
     */
    ERR_TOWN_NAMES_MUST_BE_A_SINGLE_WORD ("MsgTownNamesMustBeASingleWorld", null, "&cTown names must be a single word"),

    /**
     * Message telling user that the town specified cannot be found.
     * %1$s - invalid town name
     */
    ERR_TOWN_NOT_FOUND ("MsgTownNotFound", "%1$s - invalid town name", "&cCannot find town '%1$s'"),

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
     * Message telling user that a certain player is already a member of a
     * certain town.
     * %1$s - player name
     * %2$s - town name
     */
    ERR_PLAYER_ALREADY_MEMBER ("MsgPlayerAlreadyMember", "%1$s - player name\n# %2$s - town name", "&cPlayer '%1$s' is already a member of Town '%2$s'"),

    /**
     * Message telling user that a certain player is already a leader of a
     * certain town.
     * %1$s - player name
     * %2$s - town name
     */
    ERR_PLAYER_ALREADY_LEADER ("MsgPlayerAlreadyLeader", "%1$s - player name\n# %2$s - town name", "&cPlayer '%1$s' is already a leader in Town '%2$s'"),

    /**
     * Message telling user that a certain player is already a citizen in a
     * certain town.
     * %1$s - player name
     * %2$s - town name
     */
    ERR_PLAYER_ALREADY_CITIZEN ("MsgPlayerAlreadyCitizen", "%1$s - player name\n# %2$s - town name", "&cPlayer '%1$s' is already a citizen in Town '%2$s'"),

    /**
     * Message telling user that they have sent too many arguments
     * for a command.
     */
    ERR_TOO_MANY_ARGUMENTS ("MsgTooManyArguments", null, "&cToo many arguments"),

    /**
     * Message telling player that they cannot perform an action as they are not
     * a leader in a certain town.
     * %1$s - town name
     */
    ERR_NOT_LEADER ("MsgNotLeader", "%1$s - town name", "&cPermission denied - You are not a town leader in '%1$s'"),

    /**
     * Message telling user that a certain player is not a member of a certain
     * town.
     * %1$s - player name
     * %2$s - town name
     */
    ERR_PLAYER_NOT_MEMBER ("MsgPlayerNotMember", "%1$s - player name\n# %2$s - town name", "&cPlayer '%1$s' is not a member of Town '%2$s'"),

    /**
     * Message telling user that no town owns a specific chunk.
     * %1$s - world name
     * %2$s - chunk X
     * %3$s - chunk Z
     */
    ERR_NO_TOWN_OWNS_CHUNK ("MsgNoTownOwnsChunk", "%1$s - world name\n# %2$s - chunk X\n# %3$s - chunk Z", "&cNo town owns chunk: '%1$s (%2$s,%3$s)'"),

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
     * Message logged to file when a town is deleted.
     * %1$s - town name
     * %2$s - player name
     */
    LOG_TOWN_DELETED ("LogTownDeleted", "%1$s - town name\n# %2$s - player name", "Town '%1$s' deleted by '%2$s'"),

    /**
     * Message logged to file when a new leader is added to a town.
     * %1$s - town name
     * %2$s - leader name
     * %3$s - player name
     */
    LOG_TOWN_LEADER_ADDED ("LogTownLeaderAdded", "%1$s - town name\n# %2$s - leader name\n# %3$s - player name", "Player '%2$s' added as Leader to Town '%1$s' by '%3$s'"),

    /**
     * Message logged to file when a new chunk is claimed.
     * %1$s - town name
     * %2$s - player name
     * %3$s - world name
     * %4$s - chunk X
     * %5$s - chunk Z
     */
    LOG_CHUNK_CLAIMED ("LogChunkClaimed", "%1$s - town name\n# %2$s - player name\n# %3$s - world name\n# %4$s - chunk X\n# %5$s - chunk Z", "Chunk (%4$s, %5$s) in World '%3$s' claimed for Town '%1$s' by Player '%2$s'"),

    /**
     * Message logged to file when a chunk is unclaimed.
     * %1$s - town name
     * %2$s - player name
     * %3$s - world name
     * %4$s - chunk X
     * %5$s - chunk Z
     */
    LOG_CHUNK_UNCLAIMED ("LogChunkUnclaimed", "%1$s - town name\n# %2$s - player name\n# %3$s - world name\n# %4$s - chunk X\n# %5$s - chunk Z", "Chunk (%4$s, %5$s) in World '%3$s' unclaimed from Town '%1$s' by Player '%2$s'"),

    /**
     * Message logged to file when a citizen is added to a town.
     * %1$s - town name
     * %2$s - sender name
     * %3$s - citizen name
     */
    LOG_CITIZEN_ADDED ("LogCitizenAdded", "%1$s - town name\n# %2$s - sender name\n# %3$s - citizen name", "Citizen '%2$s' added to '%1$s' by '%3$s'"),

    /**
     * Message logged to file when a citizen is promoted to leader in a town.
     * %1$s - town name
     * %2$s - sender name
     * %3$s - citizen name
     */
    LOG_CITIZEN_PROMOTED ("LogCitizenPromoted", "%1$s - town name\n# %2$s - sender name\n# %3$s - citizen name", "Citizen '%3$s' promoted to Leader in Town '%1$s' by '%2$s'"),

    /**
     * Message logged to file when a leader is demoted to citizen in a town.
     * %1$s - town name
     * %2$s - sender name
     * %3$s - leader name
     */
    LOG_LEADER_DEMOTED ("LogLeaderDemoted", "%1$s - town name\n# %2$s - sender name\n# %3$s - leader name", "Leader '%3$s' demoted to Citizen in Town '%1$s' by '%2$s'"),

    /**
     * Message logged to file when a citizen is added to a town.
     * %1$s - town name
     * %2$s - sender name
     * %3$s - member name
     */
    LOG_TOWN_MEMBER_REMOVED ("LogTownMemberRemoved", "%1$s - town name\n# %2$s - sender name\n# %3$s - member name", "Player '%3$s' removed from Town '%1$s' by '%2$s'"),

    /**
     * Message logged to file when the logger is enabled by a user.
     * %1$s - sender name
     */
    LOG_LOGGING_ENABLED ("LogLoggingEnabled", "%1$s - sender name", "Logging enabled by '%1$s'"),

    /**
     * Message logged to file when the logger is disabled by a user.
     * %1$s - sender name
     */
    LOG_LOGGING_DISABLED ("LogLoggingDisabled", "%1$s - sender name", "Logging disabled by '%1$s'"),

    /**
     * Message telling player that a specified town already exists.
     * %1$s - town name
     */
    ERR_TOWN_ALREADY_EXISTS ("MsgTownAlreadyExists", "%1$s - town name", "&cTown named '%1$s' already exists!"),

    /**
     * Message telling sender that a player has been removed from a town.
     * %1$s - town name
     * %2$s - member name
     */
    MSG_MEMBER_REMOVED ("MsgMemberRemoved", "%1$s - town name\n# %2$s - member name", "&7Player '%2$s' removed from Town '%1$s'"),

    /**
     * Message telling user they have been removed from a town.
     * %1$s - town name
     * %2$s - sender name
     */
    MSG_REMOVED_MEMBER ("MsgRemovedMember", "%1$s - town name\n# %2$s - sender name", "&7You have been removed from Town '%1$s' by '%2$s'"),

    /**
     * Message telling player that they just added a citizen to a town.
     * %1$s - town name
     * %2$s - citizen name
     */
    MSG_CITIZEN_ADDED ("MsgCitizenAdded", "%1$s - town name\n# %2$s - citizen name", "&7Added Player '%2$s' to Town '%1$s' as a Citizen"),

    /**
     * Message telling player that they were just added to a town as a citizen.
     * %1$s - town name
     * %2$s - sender name
     */
    MSG_ADDED_AS_CITIZEN ("MsgAddedAsCitizen", "%1$s - town name\n# %2$s - sender name", "&7You were added to Town '%1$s' as a Citizen by '%2$s'"),

    /**
     * Message telling player that they just promoted a citizen to leader in a
     * town.
     * %1$s - town name
     * %2$s - citizen name
     */
    MSG_CITIZEN_PROMOTED ("MsgCitizenPromoted", "%1$s - town name\n# %2$s - citizen name", "&7Promoted Player '%2$s' to Leader in Town '%1$s'"),

    /**
     * Message telling player that they were just promoted to leader in a town.
     * %1$s - town name
     * %2$s - sender name
     */
    MSG_PROMOTED ("MsgPromoted", "%1$s - town name\n# %2$s - sender name", "&7You were promoted to Leader in Town '%1$s' by '%2$s'"),

    /**
     * Message telling player that they just demoted a leader to citizen in a
     * town.
     * %1$s - town name
     * %2$s - leader name
     */
    MSG_LEADER_DEMOTED ("MsgLeaderDemoted", "%1$s - town name\n# %2$s - leader name", "&7Demoted Player '%2$s' to Citizen in Town '%1$s'"),

    /**
     * Message telling player that they were just demoted to citizen in a town.
     * %1$s - town name
     * %2$s - sender name
     */
    MSG_DEMOTED ("MsgDemoted", "%1$s - town name\n# %2$s - sender name", "&7You were demoted to Citizen in Town '%1$s' by '%2$s'"),

    /**
     * Message telling player that they just claimed a chunk for a town.
     * %1$s - town name
     */
    MSG_CHUNK_CLAIMED ("MsgChunkClaimed", "%1$s - town name", "&7Chunk claimed for '%1$s'"),

    /**
     * Message telling player that they just unclaimed a chunk.
     * %1$s - town name
     */
    MSG_CHUNK_UNCLAIMED ("MsgChunkUnClaimed", "%1$s - town name", "&7Chunk unclaimed! No longer owned by Town '%1$s'"),

    /**
     * Message telling player that a chunk they tried to claim already belongs
     * to a town.
     * %1$s - town name
     */
    MSG_CHUNK_ALREADY_CLAIMED ("MsgChunkAlreadyClaimed", "%1$s - town name", "&cChunk already claimed by Town '%1$s'"),

    /**
     * Message to confirm to sender that a town has been created.
     * %1$s - town name
     */
    MSG_TOWN_CREATED ("MsgTownCreated", "%1$s - town name", "&6Congratulations, you created Town '%1$s'!"),

    /**
     * Message to confirm to sender that a town has been deleted.
     * %1$s - town name
     */
    MSG_TOWN_DELETED ("MsgTownDeleted", "%1$s - town name", "&dYou deleted Town '%1$s'"),

    /**
     * Message to be broadcast to server telling players that a town has been
     * created.
     * %1$s - town name
     */
    MSG_TOWN_CREATED_BROADCAST ("MsgTownCreatedBroadcast", "%1$s - town name", "&6New Town '%1$s' created!"),

    /**
     * Message to be broadcast to server telling players that a town has been
     * deleted.
     * %1$s - town name
     */
    MSG_TOWN_DELETED_BROADCAST ("MsgTownDeletedBroadcast", "%1$s - town name", "&6Town '%1$s' deleted"),

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
     * Message telling user the log status has been set to a new value.
     * %1$s - logging status
     */
    MSG_LOG_STATUS_SET ("MsgLogStatusSet", "%1$s - loggin status", "&7Logging status set to '%1$s'"),

    /**
     * Description for Help command.
     */
    DESCRIPTION_HELP ("DescHelp", null, "Shows help menu"),

    /**
     * Description for Reload command.
     */
    DESCRIPTION_RELOAD ("DescReload", null, "Reloads config values"),

    /**
     * Description for Debug command.
     */
    DESCRIPTION_DEBUG ("DescDebug", null, "Switch debug mode on or off"),

    /**
     * Description for Create command.
     */
    DESCRIPTION_CREATE ("DescCreate", null, "Creates a town with current chunk"),

    /**
     * Description for Delete command.
     */
    DESCRIPTION_DELETE ("DescDelete", null, "Deletes a specified town"),

    /**
     * Description for Claim command.
     */
    DESCRIPTION_CLAIM ("DescClaim", null, "Claims current chunk for town"),

    /**
     * Description for Unclaim command.
     */
    DESCRIPTION_UNCLAIM ("DescUnclaim", null, "Removes a town's claim to a chunk"),

    /**
     * Description for Add command.
     */
    DESCRIPTION_ADD ("DescAdd", null, "Adds citizen to a town"),

    /**
     * Description for Remove command.
     */
    DESCRIPTION_REMOVE ("DescRemove", null, "Removes a member from a town"),

    /**
     * Description for Promote command.
     */
    DESCRIPTION_PROMOTE ("DescPromote", null, "Promotes a citizen to a leader in a town"),

    /**
     * Description for Demote command.
     */
    DESCRIPTION_DEMOTE ("DescDemote", null, "Demotes a leader to a citizen in a town"),

    /**
     * Description for List command.
     */
    DESCRIPTION_LIST ("DescList", null, "Display a list of towns"),

    /**
     * Description for Info command.
     */
    DESCRIPTION_INFO ("DescInfo", null, "Display information about a town"),

    /**
     * Description for Log command.
     */
    DESCRIPTION_LOG ("DescLog", null, "Switch logging on or off"),

    /**
     * Displayed at the top of the List command.
     */
    LIST_HEADER ("ListHeader", null, "&7------ Towns ------"),

    /**
     * Displayed at for each town with the List command.
     * %1$s - town name
     */
    LIST_ENTRY ("ListEntry", "%1$s - town name", "&7%1$s"),

    /**
     * Message displayed to the user if List command is performed but no towns
     * exist.
     */
    MSG_NO_TOWNS ("MsgNoTowns", null, "&7No towns created yet!"),

    /**
     * Displayed at the top of the Info command.
     * %1$s - town name
     */
    INFO_HEADER ("InfoHeader", "%1$s - town name", "&7------ Town Info - %1$s ------"),

    /**
     * Displayed above a list of leaders of the town in the Info command.
     */
    INFO_TOWN_LEADERS_HEADER ("InfoTownLeadersHeader", null, "&dTown Leaders"),

    /**
     * Displayed for each leader in a Town in the Info command.
     * %1$s - leader name
     */
    INFO_TOWN_LEADERS_ENTRY ("InfoTownLeadersEntry", "%1$s - leader name", "&d - %1$s"),

    /**
     * Displayed above the citizens in a Town in the Info command.
     */
    INFO_TOWN_CITIZENS_HEADER ("InfoTownCitizensHeader", null, "&dTown Citizens"),

    /**
     * Displayed for each citizen in a Town in the Info command.
     * %1$s - citizen name
     */
    INFO_TOWN_CITIZENS_ENTRY ("InfoTownCitizensEntry", "%1$s - citizen name", "&d - %1$s"),

    /**
     * Displayed above the chunks for a town in the Info command.
     */
    INFO_TOWN_CHUNKS_HEADER ("InfoTownChunksHeader", null, "&dTown Chunks"),

    /**
     * Displayed for each chunk in a town in the Info command.
     * %1$s - world name
     * %2$s - chunk x
     * %1$s - chunk z
     */
    INFO_TOWN_CHUNKS_ENTRY ("InfoTownChunksEntry", "%1$s - world name\n# %2$s - chunk x\n# %3$s - chunk z", "&d - %1$s (%2$s, %3$s)"),

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
     * Private constructor.
     *
     * @param name          name of the entry, as found in the localisation file
     * @param usage         usage for the entry, used as a comment in the
     *                      localisation file to help the user, can be empty
     * @param defaultValue  default value of the entry, as found in the
     *                      localisation file
     */
    private LocalisationEntry(String name, String usage, String defaultValue) {
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