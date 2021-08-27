package me.metamechanists;

import me.metamechanists.commands.CommandHandler;
import me.metamechanists.config.CategoryConfig;
import me.metamechanists.tasks.QuestCheckTask;
import me.metamechanists.util.FileUtils;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MMQ extends JavaPlugin {

    private final int QUEST_CHECK_INTERVAL = 20;

    @Override
    public void onEnable() {
        GeneralUtils.plugin = this;
        GeneralUtils.initialize();
        FileUtils.loadAllConfigs();
        CategoryConfig.loadCategories();
        QuestCheckTask questCheckTask = new QuestCheckTask();
        questCheckTask.runTaskTimer(this, 0, QUEST_CHECK_INTERVAL);
    }

    @Override
    public void onDisable() {
        // Nothing here basically
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String alias, String[] arguments) {
        if (CommandHandler.isQuestCommand(command)) {
            CommandHandler.processQuestCommand(sender);
            return true;
        }
        if (CommandHandler.isItemStackSaveCommand(command)) {
            CommandHandler.processItemStackSaveCommand(sender);
            return true;
        }
        return false;
    }
}
