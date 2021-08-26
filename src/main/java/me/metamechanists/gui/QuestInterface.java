package me.metamechanists.gui;

import me.metamechanists.config.QuestDescriptor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.LinkedHashMap;

public class QuestInterface extends UserInterface {

    LinkedHashMap<String, QuestDescriptor> quests;

    public QuestInterface(Player player, int size, String title, LinkedHashMap<String, QuestDescriptor> quests) {
        super(player, size, title);
        this.quests = quests;
        int i = 0;
        for (QuestDescriptor quest : quests.values()) {
            if (quest.playerHasPermission(player)) {
                setItem(i++, quest.getNormalIcon());
            } else {
                setItem(i++, quest.getLockedIcon());
            }
        }
    }

    @Override
    protected void onClick(Player player, int slot, ClickType click) {
        if (quests.values().size() > slot) {
            QuestDescriptor quest = quests.values().stream().toList().get(slot);
            String name = quests.keySet().stream().toList().get(slot);
            // TODO let players accept quests
        }
    }
}