package me.metamechanists.gui;

import me.metamechanists.config.CategoryConfig;
import me.metamechanists.config.CategoryDescriptor;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class CategoryInterface extends UserInterface {

    LinkedHashMap<String, CategoryDescriptor> categories;

    public CategoryInterface(Player player, int size, String title) {
        super(player, size, title);
        categories = CategoryConfig.getCategories();
        int i = 0;
        for (CategoryDescriptor category : categories.values()) {
            setItem(i++, getIconWithPrefix(category));
        }
    }

    private ItemStack getIconWithPrefix(CategoryDescriptor category) {
        // TODO add logic for if the player has completed a category
        if (!category.playerHasPermission(player)) {
            return GeneralUtils.itemStackIconLocked(category.getName(), category.getLoreLocked());
        } else {
            return GeneralUtils.itemStackIconAvailable(category.getName(), category.getLoreAvailable(), category.getIcon());
        }
    }

    @Override
    protected void onClick(Player player, int slot, ClickType click) {
        if (categories.values().size() > slot) {
            CategoryDescriptor category = categories.values().stream().toList().get(slot);
            String name = categories.keySet().stream().toList().get(slot);
            player.sendMessage("You clicked " + name);
            new QuestInterface(player, 9, "Quests", category.getQuests()).display();
        }
    }
}