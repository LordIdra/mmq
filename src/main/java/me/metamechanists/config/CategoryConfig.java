package me.metamechanists.config;

import me.metamechanists.util.FileUtils;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryConfig {

    private static final Map<String, CategoryDescriptor> categories = new HashMap<>();

    private CategoryConfig() {}

    public static QuestDescriptor loadQuest(ConfigurationSection section) {
        ItemStack icon = section.getItemStack("icon");
        List<Permission> requiredPermissions = GeneralUtils.permissionStringsToPermissions(
                    section.getStringList("required-permissions"));
        List<Permission> rewardPermissions = GeneralUtils.permissionStringsToPermissions(
                    section.getStringList("reward-permissions"));
        List<ItemStack> requiredItems = (List<ItemStack>) section.getList("required-items");
        List<ItemStack> rewardItems = (List<ItemStack>) section.getList("reward-items");
        return new QuestDescriptor(icon, requiredPermissions, requiredItems, rewardPermissions, rewardItems);
    }

    public static List<QuestDescriptor> loadQuests(ConfigurationSection section) {
        List<QuestDescriptor> quests = new ArrayList<>();
        for (String key : section.getKeys(true)) {
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

    public static Map<String, CategoryDescriptor> getCategories() {
        return categories;
    }
}
