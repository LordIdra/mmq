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

    public static LinkedHashMap<String, QuestDescriptor> loadQuests(ConfigurationSection section) {
        LinkedHashMap<String, QuestDescriptor> quests = new LinkedHashMap<>();
        for (String key : section.getKeys(false)) {
            ConfigurationSection value = section.getConfigurationSection(key);
            quests.put(key, new QuestDescriptor(value));
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
