package me.metamechanists.config;

import me.metamechanists.util.GeneralUtils;
import me.metamechanists.util.PermissionUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.List;


public final class Category {

    private final String id;
    private final String name;
    private final ItemStack icon;
    private final List<String> loreLocked;
    private final List<String> loreActive;
    private final List<Permission> permissions;
    private final List<Quest> quests;

    public Category(String id, FileConfiguration config) {
        ConfigurationSection categoryConfig = config.getConfigurationSection("category");
        ConfigurationSection questConfig = config.getConfigurationSection("quests");
        this.id = id;
        name = categoryConfig.getString("name");
        icon = categoryConfig.getItemStack("icon");
        loreLocked = categoryConfig.getStringList("locked");
        loreActive = categoryConfig.getStringList("active");
        permissions = GeneralUtils.permissionStringsToPermissions(
                categoryConfig.getStringList("permissions"));
        quests = CategoryConfig.loadQuests(questConfig);
    }

    private Permission categoryCompletePermission() {
        return new Permission("mmq.category." + id + ".complete");
    }

    public boolean isActive(Player player) {
        if (player.hasPermission(categoryCompletePermission())) {
            return false;
        }
        for (Permission permission : permissions) {
            if (!player.hasPermission(permission)) {
                return false;
            }
        }
        return true;
    }

    public boolean allQuestsComplete(Player player) {
        for (Quest quest : quests) {
            if (!quest.isComplete(player)) {
                return false;
            }
        }
        return true;
    }

    public void complete(Player player) {
        PermissionUtils.addPermission(player, categoryCompletePermission());
    }

    public ItemStack getIcon() { return icon; }

    public List<String> getLoreLocked() {
        return loreLocked;
    }

    public List<String> getLoreActive() {
        return loreActive;
    }

    public String getName() {
        return name;
    }

    public List<Quest> getQuests() {
        return quests;
    }
}
