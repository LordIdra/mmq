package me.metamechanists.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    private static FileConfiguration categoryConfig;
    private static FileConfiguration questConfig;
    private static FileConfiguration itemstackConfig;

    private FileUtils() {}

    private static void loadFiles() {
        categoryConfig = YamlConfiguration.loadConfiguration(new File(PluginUtils.dataFolder, "categories.yml"));
        questConfig = YamlConfiguration.loadConfiguration(new File(PluginUtils.dataFolder, "quest.yml"));
        itemstackConfig = YamlConfiguration.loadConfiguration(new File(PluginUtils.dataFolder, "itemstacks.yml"));
    }

    public static void initialize() {
        PluginUtils.plugin.saveResource("categories.yml", false);
        PluginUtils.plugin.saveResource("quests.yml", false);
        PluginUtils.plugin.saveResource("itemstacks.yml", false);
        loadFiles();
    }

    public static void saveFiles() {
        try {
            categoryConfig.save(new File(PluginUtils.dataFolder, "categories.yml"));
            questConfig.save(new File(PluginUtils.dataFolder, "quest.yml"));
            itemstackConfig.save(new File(PluginUtils.dataFolder, "itemstacks.yml"));
        } catch (IOException e) {
            PluginUtils.plugin.getLogger().severe(ChatColor.RED + "Failed to save config files.");
            e.printStackTrace();
        }
        loadFiles();
    }

    public static void writeStack(Player player, ItemStack stack) {
        itemstackConfig.set(player.getName(), stack);
        saveFiles();
    }
}
