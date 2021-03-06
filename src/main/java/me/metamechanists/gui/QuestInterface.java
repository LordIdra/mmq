package me.metamechanists.gui;

import me.metamechanists.config.CategoryConfig;
import me.metamechanists.config.Quest;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class QuestInterface extends UserInterface {

    Map<Integer, Quest> quests;

    public QuestInterface(Player player, int size, String title, Map<Integer, Quest> quests) {
        super(player, size, title);
        this.quests = quests;
        setItem(0, GeneralUtils.itemStackReturn());
        for (int slot : quests.keySet()) {
            Quest quest = quests.get(slot);
            setItem(slot, getIconWithPrefix(quest));
        }
    }

    private ItemStack getIconWithPrefix(Quest quest) {
        if (quest.isComplete(player)) {
            return GeneralUtils.itemStackIconComplete(quest.getName());
        } else if (quest.isActive(player)) {
            return GeneralUtils.itemStackIconActive(quest.getName(), quest.getLoreActive(), quest.getIcon());
        } else {
            return GeneralUtils.itemStackIconLocked(quest.getName(), quest.getLoreLocked());
        }
    }

    @Override
    protected void onClick(Player player, int slot, ClickType click) {
        if (slot == 0) {
            int size = 9;
            new CategoryInterface(player, size, "Categories").display();
        } else {
            player.sendMessage(ChatColor.RED +"No need to click on a quest to accept it! Quests are " +
                    "detected and completed automatically.");
        }
    }
}