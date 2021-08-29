package me.metamechanists.config;

import me.metamechanists.util.FileUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class CategoryConfig {

    private static final Map<Integer, Category> categories = new HashMap<>();

    private CategoryConfig() {}

    public static Map<Integer, Quest> loadQuests(ConfigurationSection section) {
        Map<Integer, Quest> quests = new HashMap<>();
        for (String key : section.getKeys(false)) {
            ConfigurationSection value = section.getConfigurationSection(key);
            int slot = value.getInt("slot");
            quests.put(slot, new Quest(value));
        }
        return quests;
    }

    public static void loadCategories() {
        Map<String, FileConfiguration> configs = FileUtils.getCategoryConfigs();
        for (String key : configs.keySet()) {
            FileConfiguration value = configs.get(key);
            if (value != null) {
                ConfigurationSection section = value.getConfigurationSection("category");
                int slot = section.getInt("slot");
                categories.put(slot, new Category(key, value));
            }
        }
    }

    public static Map<Integer, Category> getCategories() {
        return categories;
    }
}
