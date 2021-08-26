package me.metamechanists.config;

import me.metamechanists.util.GeneralUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.List;
import java.util.Objects;


public final class CategoryDescriptor {

    private final ItemStack normalIcon;
    private final List<Permission> permissions;
    private final List<QuestDescriptor> quests;
    private final ItemStack lockedIcon;

    public CategoryDescriptor(FileConfiguration config) {
        ConfigurationSection categoryConfig = config.getConfigurationSection("category");
        ConfigurationSection questConfig = config.getConfigurationSection("quests");
        normalIcon = categoryConfig.getItemStack("normal-icon");
        lockedIcon = categoryConfig.getItemStack("locked-icon");
        permissions = GeneralUtils.permissionStringsToPermissions(
                categoryConfig.getStringList("permissions"));
        quests = CategoryConfig.loadQuests(questConfig);
        if (normalIcon == null) {
            GeneralUtils.plugin.getLogger().severe("Category " + config.getName() + " missing icon.");
        }
    }

    public ItemStack getNormalIcon() {
        return normalIcon;
    }

    public ItemStack getLockedIcon() {
        return lockedIcon;
    }

    public List<QuestDescriptor> getQuests() {
        return quests;
    }

    public boolean playerHasPermission(Player player) {
        for (Permission permission : permissions) {
            if (!player.hasPermission(permission)) {
                return false;
            }
        }
        return true;
    }

    public List<Permission> permissions() {
        return permissions;
    }

    public List<QuestDescriptor> quests() {
        return quests;
    }
}
