package me.metamechanists.gui;

import me.metamechanists.config.CategoryConfig;
import me.metamechanists.config.CategoryDescriptor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedHashMap;

public class CategoryInterface extends UserInterface {

    LinkedHashMap<String, CategoryDescriptor> categories;

    private ItemStack itemStackPrefixLocked(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§f§l[§4§lLOCKED§f§l] §c" + meta.getDisplayName());
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack itemStackPrefixAvailable(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§f§l[§6§lAVAILABLE§f§l] §e" + meta.getDisplayName());
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack itemStackPrefixCompleted(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§f§l[§2§lCOMPLETED§f§l] §a" + meta.getDisplayName());
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack getIconWithPrefix(CategoryDescriptor category) {
        // TODO add logic for if the player has completed a quest

        // TODO tomorrow - replace the 2 different icons with a single
        // TODO icon and two pieces of lore (locked-message and available-message)
        // TODO the icon name will be formatted automatically from the quest name
        // TODO the 'completed' message will be implemented later and automatically
        if (!category.playerHasPermission(player)) {
            return itemStackPrefixLocked(category.getLockedIcon());
        } else {
            return itemStackPrefixLocked(category.getAvailableIcon());
        }
    }

    public CategoryInterface(Player player, int size, String title) {
        super(player, size, title);
        categories = CategoryConfig.getCategories();
        int i = 0;
        for (CategoryDescriptor category : categories.values()) {
            setItem(i++, getIconWithPrefix(category));
            // TODO add 'available' before the quest name if the player has unlocked the quest but hasn't accepted it yet
        }
    }

    @Override
    protected void onClick(Player player, int slot, ClickType click) {
        if (categories.values().size() > slot) {
            CategoryDescriptor category = categories.values().stream().toList().get(slot);
            String name = categories.keySet().stream().toList().get(slot);
            player.sendMessage("You clicked " + name);
        }
    }
}