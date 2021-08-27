package me.metamechanists.config;

import me.metamechanists.util.GeneralUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.LinkedHashMap;
import java.util.List;


public final class CategoryDescriptor {

    private final String name;
    private final ItemStack icon;
    private final List<String> loreLocked;
    private final List<String> loreAvailable;
    private final List<Permission> permissions;
    private final LinkedHashMap<String, QuestDescriptor> quests;

    public CategoryDescriptor(FileConfiguration config) {
        ConfigurationSection categoryConfig = config.getConfigurationSection("category");
        ConfigurationSection questConfig = config.getConfigurationSection("quests");
        name = categoryConfig.getString("name");
        icon = categoryConfig.getItemStack("icon");
        loreLocked = categoryConfig.getStringList("locked");
        loreAvailable = categoryConfig.getStringList("available");
        permissions = GeneralUtils.permissionStringsToPermissions(
                categoryConfig.getStringList("permissions"));
        quests = CategoryConfig.loadQuests(questConfig);
    }

    public boolean playerHasPermission(Player player) {
        for (Permission permission : permissions) {
            if (!player.hasPermission(permission)) {
                return false;
            }
        }
        return true;
    }

    public ItemStack getIcon() { return icon; }

    public List<String> getLoreLocked() {
        return loreLocked;
    }

    public List<String> getLoreAvailable() {
        return loreAvailable;
    }

    public String getName() {
        return name;
    }

    public LinkedHashMap<String, QuestDescriptor> getQuests() {
        return quests;
    }
}
