package me.metamechanists.config;

import me.metamechanists.util.FileUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class CategoryConfig {

    private static final List<Category> categories = new ArrayList<>();

    private CategoryConfig() {}

    public static List<Quest> loadQuests(ConfigurationSection section) {
        List<Quest> quests = new ArrayList<>();
        for (String key : section.getKeys(false)) {
            ConfigurationSection value = section.getConfigurationSection(key);
            quests.add(new Quest(value));
        }
        return quests;
    }

    public static void loadCategories() {
        Map<String, FileConfiguration> configs = FileUtils.getCategoryConfigs();
        for (String key : configs.keySet()) {
            FileConfiguration value = configs.get(key);
            if (value != null) {
                categories.add(new Category(value));
            }
        }
    }

    public static List<Category> getCategories() {
        return categories;
    }
}
