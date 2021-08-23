package me.metamechanists.config;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class CategoryConfig {

    private static final Map<String, CategoryDescriptor> categoryMap = new HashMap<>();

    private CategoryConfig() {}

    public static void load() {
        // TODO load categories.yml into categoryMap
    }

    public static CategoryDescriptor getCategory(String id) {
        if (!categoryMap.containsKey(id)) {
            Bukkit.getServer().getLogger().severe("Quest category with ID " + id + " not found");
        }
        return categoryMap.get(id);
    }
}
