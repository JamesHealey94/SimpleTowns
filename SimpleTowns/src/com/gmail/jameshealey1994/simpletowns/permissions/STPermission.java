package com.gmail.jameshealey1994.simpletowns.permissions;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;

/**
 * Enum defining the permissions for SimpleTownsCommands.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public enum STPermission {
		
    /**
     * Permission for building in the wilderness above the Mine Y value.
     */
    BUILD_WILDERNESS ("simpletowns.build.wilderness"),

    /**
     * Permission for building below the Mine Y value.
     */
    BUILD_MINES ("simpletowns.build.mines"),

    /**
     * Permission for building in towns that they're a member of.
     */
    BUILD_TOWNS ("simpletowns.build.towns"),

    /**
     * Permission for InfoCommand.
     */
    INFO ("simpletowns.info"),

    /**
     * Permission for DebugCommand.
     */
    DEBUG ("simpletowns.debug"),

    /**
     * Permission for LogCommand.
     */
    LOG ("simpletowns.log"),

    /**
     * Permission for ReloadCommand.
     */
    RELOAD ("simpletowns.reload"),

    /**
     * Permission for CreateCommand.
     */
    CREATE ("simpletowns.create"),

    /**
     * Permission for DeleteCommand.
     */
    DELETE ("simpletowns.delete"),

    /**
     * Permission for ClaimCommand.
     */
    CLAIM ("simpletowns.claim"),

    /**
     * Permission for ListCommand.
     */
    LIST ("simpletowns.list"),

    /**
     * Permission used by admins to override.
     */
    ADMIN ("simpletowns.admin");
    
    /**
     * The name of the permission.
     */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name of the permission
     */
    private STPermission(String name) {
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
    
    private HashMap<String, Permission> permissionMap = new HashMap<String, Permission>();
    
    /**
     * Returns Permission of the enum.
     *
     * @return  Permission of the enum
     */
    public Permission getPermission() {
    	
    	if(!permissionMap.containsKey(this.getName().toLowerCase())) {
    		permissionMap.put(this.getName().toLowerCase(), new Permission(this.getName()));
    	}
    	
    	return permissionMap.get(this.getName().toLowerCase());
    	
    	// I couldn't find a way to detect if a permission was already registered, so I'm using
    	// our own HashMap to store this data.
    	
    	/*
    	// Check if the permission exists
    	if(Bukkit.getServer().getPluginManager().getPermission(this.getName()) == null) {
    		// Add the permission object to bukkit
    		try { 
    			Bukkit.getServer().getPluginManager().addPermission();
    		} catch(IllegalArgumentException e) {
    			
    		}
    	}
    	
    	// Send back an already created permission object
        return (Bukkit.getServer().getPluginManager().getPermission(this.getName()));
        */
    }
}