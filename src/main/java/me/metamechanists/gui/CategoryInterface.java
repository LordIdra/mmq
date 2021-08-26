package me.metamechanists.gui;

import me.metamechanists.config.CategoryConfig;
import me.metamechanists.config.CategoryDescriptor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.LinkedHashMap;

public class CategoryInterface extends UserInterface {

    LinkedHashMap<String, CategoryDescriptor> categories;

    public CategoryInterface(Player player, int size, String title) {
        super(player, size, title);
        categories = CategoryConfig.getCategories();
        int i = 0;
        for (CategoryDescriptor category : categories.values()) {
            if (category.playerHasPermission(player)) {
                setItem(i++, category.getNormalIcon());
            } else {
                setItem(i++, category.getLockedIcon());
            }
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