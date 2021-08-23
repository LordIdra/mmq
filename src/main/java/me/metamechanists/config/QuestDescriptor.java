package me.metamechanists.config;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;


public record QuestDescriptor(
        ItemStack icon,
        Permission[] requiredPermissions,
        ItemStack[] requiredItems,
        Permission[] rewardPermissions,
        ItemStack[] rewardItems) {

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

    public boolean playerHasItems(Player player) {
        // TODO item comparison check
        return false;
    }

    public void rewardPlayer(Player player) {
        // TODO give player reward items and permissions
    }
}
