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

    private final ItemStack icon;
    private final List<Permission> permissions;
    private final List<QuestDescriptor> quests;

    public CategoryDescriptor(FileConfiguration config) {
        icon = config.getItemStack("icon");
        permissions = GeneralUtils.permissionStringsToPermissions(
                config.getStringList("permissions"));
        ConfigurationSection questConfig = config.getConfigurationSection("quests");
        quests = CategoryConfig.loadQuests(config);
        if (icon == null) {
            GeneralUtils.plugin.getLogger().severe("Category " + config.getName() + " missing icon.");
        }
    }

    public ItemStack getIcon() {
        return icon;
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

    public ItemStack icon() {
        return icon;
    }

    public List<Permission> permissions() {
        return permissions;
    }

    public List<QuestDescriptor> quests() {
        return quests;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CategoryDescriptor) obj;
        return Objects.equals(this.icon, that.icon) &&
                Objects.equals(this.permissions, that.permissions) &&
                Objects.equals(this.quests, that.quests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icon, permissions, quests);
    }

    @Override
    public String toString() {
        return "CategoryDescriptor[" +
                "icon=" + icon + ", " +
                "permissions=" + permissions + ", " +
                "quests=" + quests + ']';
    }

}
