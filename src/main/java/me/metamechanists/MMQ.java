package me.metamechanists;

import me.metamechanists.commands.CommandHandler;
import me.metamechanists.config.CategoryConfig;
import me.metamechanists.config.QuestConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MMQ extends JavaPlugin {

    @Override
    public void onEnable() {
        CategoryConfig.load();
        QuestConfig.load();
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
