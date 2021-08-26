package me.metamechanists.util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneralUtils {

    public static Plugin plugin;
    public static File dataFolder;

    public static void initialize() {
        dataFolder = plugin.getDataFolder();
    }

    public static List<Permission> permissionStringsToPermissions(List<String> permissionsAsStrings) {
        List<Permission> permissions = new ArrayList<>();
        for (String permissionString : permissionsAsStrings) {
            permissions.add(new Permission(permissionString));
        }
        return permissions;
    }

    public static String fileNameNoExtension(File file) {
        return file.getName().substring(0, file.getName().indexOf('.'));
    }
}
