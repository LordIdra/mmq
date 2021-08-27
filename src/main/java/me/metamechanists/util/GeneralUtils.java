package me.metamechanists.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneralUtils {

    public static Plugin plugin;
    public static File dataFolder;
    private static final String LOCKED_PREFIX =
            ChatColor.translateAlternateColorCodes('&', "&f&l[&4&lLOCKED&f&l] &c");
    private static final String AVAILABLE_PREFIX =
            ChatColor.translateAlternateColorCodes('&', "&f&l[&6&lAVAILABLE&f&l] &e");
    private static final String COMPLETED_PREFIX =
            ChatColor.translateAlternateColorCodes('&', "&f&l[&2&lCOMPLETED&f&l] &a");

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

    public static ItemStack itemStackIconLocked(String name, List<String> lore) {
        ItemStack stack = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(LOCKED_PREFIX  + name);
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    public static ItemStack itemStackIconAvailable(String name, List<String> lore, ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(AVAILABLE_PREFIX  + name);
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    public static ItemStack itemStackIconCompleted(String name) {
        ItemStack stack = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(COMPLETED_PREFIX  + name);
        stack.setItemMeta(meta);
        return stack;
    }
}
