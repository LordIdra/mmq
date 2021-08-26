package me.metamechanists.config;

import me.metamechanists.util.PermissionUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.List;
import java.util.Map;

import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar;


public record QuestDescriptor(
        ItemStack icon,
        List<Permission> requiredPermissions,
        List<ItemStack> requiredItems,
        List<Permission> rewardPermissions,
        List<ItemStack> rewardItems) {

    private static boolean playerHasItemStack(Player player, ItemStack target) {
        int itemCount = 0;
        for (ItemStack actual : player.getInventory().getContents()) {
            if (isItemSimilar(actual, target, true, false)) {
                itemCount += actual.getAmount();
            }
        }
        return itemCount > target.getAmount();
    }

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
        for (ItemStack target : requiredItems) {
            if (!playerHasItemStack(player, target)) {
                return false;
            }
        }
        return true;
    }

    public void rewardPlayerItems(Player player) {
        for (ItemStack addStack : rewardItems) {
            Map<Integer, ItemStack> items_to_drop = player.getInventory().addItem(addStack);
            for (ItemStack dropStack : items_to_drop.values()) {
                player.getWorld().dropItem(player.getLocation(), dropStack);
            }
        }
    }

    public void rewardPlayerPermissions(Player player) {
        for (Permission permission : rewardPermissions) {
            PermissionUtils.addPermission(player, permission);
        }
    }
}
