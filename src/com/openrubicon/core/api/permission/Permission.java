package com.openrubicon.core.api.permission;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.permission.interfaces.PermissionNode;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Permission {

    private Player player;

    public Permission(Player player) {
        this.player = player;
    }

    public boolean has(PermissionNode permissionNode)
    {
        return Permission.has(this.player, permissionNode);
    }

    public static boolean has(Player player, PermissionNode permissionNode)
    {
        if(permissionNode.isCreativeOverride() && player.getGameMode() == GameMode.CREATIVE)
            return true;

        if(permissionNode.isOpOverride() && player.isOp())
            return true;

        if(RRPGCore.permission.has(player, permissionNode.getNode()))
            return true;

        return false;
    }

}
