package me.metamechanists.config;

import me.metamechanists.util.GeneralUtils;
import me.metamechanists.util.PermissionUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.List;
import java.util.Map;

import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar;

public final class QuestDescriptor {

    private final String name;
    private final ItemStack icon;
    private final List<String> loreLocked;
    private final List<String> loreAvailable;
    private final List<Permission> requiredPermissions;
    private final ItemStack requiredItem;
    private final List<Permission> rewardPermissions;
    private final ItemStack rewardItem;

    public QuestDescriptor(ConfigurationSection section) {
        name = section.getString("name");
        icon = section.getItemStack("icon");
        loreLocked = section.getStringList("locked");
        loreAvailable = section.getStringList("available");
        requiredPermissions = GeneralUtils.permissionStringsToPermissions(
                section.getStringList("required-permissions"));
        rewardPermissions = GeneralUtils.permissionStringsToPermissions(
                section.getStringList("reward-permissions"));
        requiredItem = section.getItemStack("required-items");
        rewardItem = section.getItemStack("reward-items");
    }

    private static boolean playerHasItemStack(Player player, ItemStack target) {
        int itemCount = 0;
        for (ItemStack actual : player.getInventory().getContents()) {
            if (isItemSimilar(actual, target, true, false)) {
                itemCount += actual.getAmount();
            }
        }
        return itemCount > target.getAmount();
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
        return playerHasItemStack(player, requiredItem);
    }

    public void rewardPlayerItems(Player player) {
        Map<Integer, ItemStack> items_to_drop = player.getInventory().addItem(rewardItem);
        for (ItemStack dropStack : items_to_drop.values()) {
            player.getWorld().dropItem(player.getLocation(), dropStack);
        }
    }

    public void rewardPlayerPermissions(Player player) {
        for (Permission permission : rewardPermissions) {
            PermissionUtils.addPermission(player, permission);
        }
    }

    public String getName() {
        return name;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public List<String> getLoreLocked() {
        return loreLocked;
    }

    public List<String> getLoreAvailable() {
        return loreAvailable;
    }
}
