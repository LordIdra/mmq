package me.metamechanists.gui;

import me.metamechanists.config.CategoryConfig;
import me.metamechanists.config.QuestDescriptor;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class QuestInterface extends UserInterface {

    LinkedHashMap<String, QuestDescriptor> quests;

    public QuestInterface(Player player, int size, String title, LinkedHashMap<String, QuestDescriptor> quests) {
        super(player, size, title);
        this.quests = quests;
        int i = 0;
        setItem(i++, GeneralUtils.itemStackReturn());
        for (QuestDescriptor quest : quests.values()) {
            setItem(i++, getIconWithPrefix(quest));
        }
    }

    private ItemStack getIconWithPrefix(QuestDescriptor quest) {
        // TODO add logic for if the player has completed a quest
        // TODO add logic for active quests
        if (!quest.playerHasPermission(player)) {
            return GeneralUtils.itemStackIconLocked(quest.getName(), quest.getLoreLocked());
        } else {
            return GeneralUtils.itemStackIconActive(quest.getName(), quest.getLoreAvailable(), quest.getIcon());
        }
    }

    @Override
    protected void onClick(Player player, int slot, ClickType click) {
        if (slot == 0) {
            int size = (int) Math.ceil(CategoryConfig.getCategories().size() / 9.0) * 9;
            new CategoryInterface(player, size, "Categories").display();
        }
        if (quests.values().size() >= slot) {
            QuestDescriptor quest = quests.values().stream().toList().get(slot);
            // TODO let players accept quests (if they have them unlocked)
        }
    }
}