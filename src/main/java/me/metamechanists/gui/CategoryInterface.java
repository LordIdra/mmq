package me.metamechanists.gui;

import me.metamechanists.config.CategoryConfig;
import me.metamechanists.config.Category;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CategoryInterface extends UserInterface {

    List<Category> categories;

    public CategoryInterface(Player player, int size, String title) {
        super(player, size, title);
        categories = CategoryConfig.getCategories();
        int i = 0;
        for (Category category : categories) {
            setItem(i++, getIconWithPrefix(category));
        }
    }

    private ItemStack getIconWithPrefix(Category category) {
        // TODO add logic for if the player has completed a category
        if (!category.playerHasPermission(player)) {
            return GeneralUtils.itemStackIconLocked(category.getName(), category.getLoreLocked());
        } else {
            return GeneralUtils.itemStackIconActive(category.getName(), category.getLoreActive(), category.getIcon());
        }
    }

    @Override
    protected void onClick(Player player, int slot, ClickType click) {
        if (categories.size() > slot) {
            Category category = categories.get(slot);
            if (category.playerHasPermission(player)) {
                int size = (int) Math.ceil(category.getQuests().size() / 9.0) * 9;
                new QuestInterface(player, size, "Quests", category.getQuests()).display();
            }
        }
    }
}