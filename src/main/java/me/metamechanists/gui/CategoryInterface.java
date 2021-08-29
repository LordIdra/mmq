package me.metamechanists.gui;

import me.metamechanists.config.CategoryConfig;
import me.metamechanists.config.Category;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class CategoryInterface extends UserInterface {

    Map<Integer, Category> categories;

    public CategoryInterface(Player player, int size, String title) {
        super(player, size, title);
        categories = CategoryConfig.getCategories();
        for (int slot : categories.keySet()) {
            Category category = categories.get(slot);
            setItem(slot, getIconWithPrefix(category));
        }
    }

    private ItemStack getIconWithPrefix(Category category) {
        if (category.allQuestsComplete(player)) {
            return GeneralUtils.itemStackIconComplete(category.getName());
        } else if (category.isActive(player)) {
            return GeneralUtils.itemStackIconActive(category.getName(), category.getLoreActive(), category.getIcon());
        } else {
            return GeneralUtils.itemStackIconLocked(category.getName(), category.getLoreLocked());
        }
    }

    @Override
    protected void onClick(Player player, int slot, ClickType click) {
        if (categories.size() > slot) {
            Category category = categories.get(slot);
            if (category.isActive(player)) {
                int size = 45;
                new QuestInterface(player, size, "Quests", category.getQuests()).display();
            }
        }
    }
}