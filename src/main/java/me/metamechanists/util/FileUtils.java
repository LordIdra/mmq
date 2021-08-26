package me.metamechanists.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileUtils {

    private static final List<File> categoryFiles = new ArrayList<>();
    private static File itemstackFile;

    private static Map<String, FileConfiguration> categoryConfigs = new HashMap<>();
    private static YamlConfiguration itemstackConfig;

    private FileUtils() {}

    private static void loadItemStackConfig() {
        GeneralUtils.plugin.saveResource("itemstacks.yml", false);
        itemstackFile = new File(GeneralUtils.dataFolder, "itemstacks.yml");
        itemstackConfig = YamlConfiguration.loadConfiguration(itemstackFile);
    }

    private static void loadCategoryConfigs() {
        File categoryFolder = new File(GeneralUtils.dataFolder, "categories");
        if (!categoryFolder.exists()) {
            categoryFolder.mkdir();
        }
        categoryFiles.addAll(Arrays.asList(categoryFolder.listFiles()));
        for (File category : categoryFiles) {
            categoryConfigs.put(GeneralUtils.fileNameNoExtension(category), YamlConfiguration.loadConfiguration(category));
        }
    }

    private static void saveItemStackConfig() {
        try {
            itemstackConfig.save(itemstackFile);
        } catch (IOException e) {
            GeneralUtils.plugin.getLogger().severe(ChatColor.RED + "Failed to save itemstacks.yml");
            e.printStackTrace();
        }
    }

    public static void loadAllConfigs() {
        loadItemStackConfig();
        loadCategoryConfigs();
    }

    public static void writeStack(@NotNull Player player, ItemStack stack) {
        itemstackConfig.set(player.getName(), stack);
        saveItemStackConfig();
    }

    public static @NotNull Map<String, FileConfiguration> getCategoryConfigs() {
        return categoryConfigs;
    }
}