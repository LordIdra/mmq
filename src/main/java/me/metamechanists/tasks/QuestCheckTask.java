package me.metamechanists.tasks;

import me.metamechanists.config.Category;
import me.metamechanists.config.CategoryConfig;
import me.metamechanists.config.Quest;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class QuestCheckTask extends BukkitRunnable {

    private static List<Category> getActiveCategories(Player player) {
        List<Category> activeCategories = new ArrayList<>();
        for (Category category : CategoryConfig.getCategories()) {
            if (category.playerHasPermission(player)) {
                activeCategories.add(category);
            }
        }
        return activeCategories;
    }

    private static List<Quest> getActiveQuests(Player player) {
        List<Quest> activeQuests = new ArrayList<>();
        for (Category category : getActiveCategories(player)) {
            for (Quest quest : category.getQuests()) {
                if (quest.playerHasPermission(player)) {
                    activeQuests.add(quest);
                }
            }
        }
        return activeQuests;
    }

    private static void completeQuests(Player player) {
        for (Quest quest : getActiveQuests(player)) {
            if (quest.playerHasItems(player)) {
                quest.complete(player);
            }
        }
    }

    private static void completeCategories(Player player) {
        for (Category category : getActiveCategories(player)) {
            if (category.allQuestsComplete(player)) {
                category.complete(player);
            }
        }
    }

    @Override
    public void run() {
        for (Player player : GeneralUtils.plugin.getServer().getOnlinePlayers()) {
            completeQuests(player);
            completeCategories(player);
        }
    }
}
