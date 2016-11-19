/*
 * Copyright (C) 2011-2014 lishid.  All rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation,  version 3.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.lishid.openinv.internal.v1_4_5;

import com.lishid.openinv.internal.IPlayerDataManager;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

// Volatile
import net.minecraft.server.v1_4_5.EntityPlayer;
import net.minecraft.server.v1_4_5.ItemInWorldManager;
import net.minecraft.server.v1_4_5.MinecraftServer;

import org.bukkit.craftbukkit.v1_4_5.CraftServer;

public class PlayerDataManager implements IPlayerDataManager {

    @Override
    public Player loadPlayer(OfflinePlayer offline) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();

        // Create an entity to load the player data
        EntityPlayer entity = new EntityPlayer(server, server.getWorldServer(0), offline.getName(), new ItemInWorldManager(server.getWorldServer(0)));

        // Get the bukkit entity
        Player target = (entity == null) ? null : entity.getBukkitEntity();
        if (target != null) {
            // Load data
            target.loadData();
            // Return the entity
            return target;
        }
        return null;
    }

    @Override
    public String getPlayerDataID(OfflinePlayer player) {
        return player.getName();
    }

}