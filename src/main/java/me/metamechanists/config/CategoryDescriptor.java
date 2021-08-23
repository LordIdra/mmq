package me.metamechanists.config;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;


public record CategoryDescriptor(ItemStack icon, Permission[] requiredPermissions) {

    public ItemStack getIcon() {
        return icon;
    }

    public boolean playerHasPermission(Player player) {
        for (Permission permission : requiredPermissions) {
            if (!player.hasPermission(permission)) {
                return false;
            }
        }
        return true;
    }
}
