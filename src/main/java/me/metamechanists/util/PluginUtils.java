package me.metamechanists.util;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class PluginUtils {

    public static Plugin plugin;
    public static File dataFolder;

    public static void initialize() {
        dataFolder = plugin.getDataFolder();
    }
}
