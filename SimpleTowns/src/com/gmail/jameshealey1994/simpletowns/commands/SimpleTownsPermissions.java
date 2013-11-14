package com.gmail.jameshealey1994.simpletowns.commands;

import org.bukkit.permissions.Permission;

/**
 * Enum defining the permissions for SimpleTownsCommands.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public enum SimpleTownsPermissions {

    /**
     * Permission for InfoCommand.
     */
    INFO ("towns.info"),

    /**
     * Permission for DebugCommand.
     */
    DEBUG ("towns.debug"),

    /**
     * Permission for ReloadCommand.
     */
    RELOAD ("towns.reload");

    /**
     * The name of the permission.
     */
    private String name;

    /**
     * Constructor.
     *
     * @param name  the name of the permission
     */
    SimpleTownsPermissions(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the Permission.
     *
     * @return  name of the Permission
     */
    public String getName() {
        return name;
    }

    /**
     * Returns Permission of the enum.
     *
     * @return  Permission of the enum
     */
    public Permission getPermission() {
        return new Permission(this.getName());
    }
}