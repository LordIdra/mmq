package me.metamechanists;

import me.metamechanists.commands.CommandHandler;
import me.metamechanists.config.CategoryConfig;
import me.metamechanists.util.FileUtils;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MMQ extends JavaPlugin {

    @Override
    public void onEnable() {
        GeneralUtils.plugin = this;
        GeneralUtils.initialize();
        FileUtils.loadAllConfigs();
        CategoryConfig.loadCategories();
        // TODO load template quests into QuestStorage
        // TODO load active quests into QuestStorage
    }

    @Override
    public void onDisable() {
        // TODO halt active tasks
        // TODO save active quests
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
