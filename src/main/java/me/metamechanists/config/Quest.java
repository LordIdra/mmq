package me.metamechanists.config;

import me.metamechanists.util.GeneralUtils;
import me.metamechanists.util.PermissionUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.List;
import java.util.Map;

import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar;

public final class Quest {

    private final String id;
    private final String name;
    private final ItemStack icon;
    private final List<String> loreLocked;
    private final List<String> loreActive;
    private final List<Permission> requiredPermissions;
    private final ItemStack requiredItem;
    private final List<Permission> rewardPermissions;
    private final ItemStack rewardItem;

    public Quest(ConfigurationSection section) {
        id = section.getName();
        name = section.getString("name");
        icon = section.getItemStack("icon");
        loreLocked = section.getStringList("locked");
        loreActive = section.getStringList("active");
        requiredPermissions = GeneralUtils.permissionStringsToPermissions(
                section.getStringList("required-permissions"));
        rewardPermissions = GeneralUtils.permissionStringsToPermissions(
                section.getStringList("reward-permissions"));
        requiredItem = section.getItemStack("required-item");
        rewardItem = section.getItemStack("reward-item");
    }

    private Permission questCompletePermission() {
        return new Permission("mmq.quest." + id + ".complete");
    }

    private void rewardPlayerItem(Player player) {
        Map<Integer, ItemStack> items_to_drop = player.getInventory().addItem(rewardItem);
        for (ItemStack dropStack : items_to_drop.values()) {
            player.getWorld().dropItem(player.getLocation(), dropStack);
        }
    }

    private void rewardPlayerPermissions(Player player) {
        for (Permission permission : rewardPermissions) {
            PermissionUtils.addPermission(player, permission);
        }
    }

    public boolean playerHasItem(Player player) {
        int itemCount = 0;
        for (ItemStack actual : player.getInventory()) {
            if (actual != null && isItemSimilar(actual, requiredItem, true, false)) {
                itemCount += actual.getAmount();
            }
        }
        return itemCount >= requiredItem.getAmount();
    }

    public boolean isActive(Player player) {
        GeneralUtils.plugin.getLogger().info(questCompletePermission().getName());
        if (player.hasPermission(questCompletePermission())) {
            GeneralUtils.plugin.getLogger().info("sky's mum is scary");
            return false;
        }
        for (Permission permission : requiredPermissions) {
            if (!player.hasPermission(permission)) {
                return false;
            }
        }
        return true;
    }

    public void complete(Player player) {
        if (PermissionUtils.addPermission(player, questCompletePermission())) {
            rewardPlayerItem(player);
            rewardPlayerPermissions(player);
            String rewardName;
            if (rewardItem.getItemMeta().hasDisplayName()) {
                rewardName = rewardItem.getItemMeta().getDisplayName();
            } else {
                rewardName = rewardItem.getType().toString().toLowerCase();
            }
            String headerFooter = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[=]";
            String globalMessage = ChatColor.BLUE + "" + player.getDisplayName() + ChatColor.GRAY +
                    " has completed the quest " + ChatColor.BLUE + name + ChatColor.RESET + ChatColor.GRAY + "!";
            String localMessage = ChatColor.DARK_PURPLE + "Quest Reward: " + ChatColor.LIGHT_PURPLE +
                    rewardName + ChatColor.YELLOW + " x" + rewardItem.getAmount();
            player.sendMessage(headerFooter);
            GeneralUtils.plugin.getServer().broadcastMessage(globalMessage);
            player.sendMessage(localMessage);
            player.sendMessage(headerFooter);
        }
    }

    public boolean isComplete(Player player) {
        return player.hasPermission(questCompletePermission());
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

    public List<String> getLoreActive() {
        return loreActive;
    }
}
