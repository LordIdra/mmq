package me.metamechanists.config;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public record CategoryDescriptor(ItemStack icon, String[] requiredPermissions) {

    public ItemStack getIcon() {
        return icon;
    }

    public boolean playerHasPermission(Player player) {
        // TODO check if player has all the permissions in requiredPermissions
    }
}
