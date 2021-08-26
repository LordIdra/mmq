package me.metamechanists.config;

import me.metamechanists.util.FileUtils;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.*;

public class CategoryConfig {

    private static final LinkedHashMap<String, CategoryDescriptor> categories = new LinkedHashMap<>();

    private CategoryConfig() {}

    public static QuestDescriptor loadQuest(ConfigurationSection section) {
        ItemStack icon = section.getItemStack("icon");
        List<Permission> requiredPermissions = GeneralUtils.permissionStringsToPermissions(
                    section.getStringList("required-permissions"));
        List<Permission> rewardPermissions = GeneralUtils.permissionStringsToPermissions(
                    section.getStringList("reward-permissions"));
        ItemStack requiredItem = section.getItemStack("required-items");
        ItemStack rewardItem = section.getItemStack("reward-items");
        return new QuestDescriptor(icon, requiredPermissions, requiredItem, rewardPermissions, rewardItem);
    }

    public static List<QuestDescriptor> loadQuests(ConfigurationSection section) {
        List<QuestDescriptor> quests = new ArrayList<>();
        for (String key : section.getKeys(false)) {
            ConfigurationSection value = section.getConfigurationSection(key);
            quests.add(loadQuest(value));
        }
        return quests;
    }

    public static void loadCategories() {
        Map<String, FileConfiguration> configs = FileUtils.getCategoryConfigs();
        for (String key : configs.keySet()) {
            FileConfiguration value = configs.get(key);
            if (value != null) {
                categories.put(key, new CategoryDescriptor(value));
            }
        }
    }

    public static LinkedHashMap<String, CategoryDescriptor> getCategories() {
        return categories;
    }
}
