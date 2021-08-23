package me.metamechanists;

import me.metamechanists.commands.CommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MMQ extends JavaPlugin {

    @Override
    public void onEnable() {
        // TODO load MessageConfig
        // TODO load CategoryConfig
        // TODO load QuestConfig
        // TODO load CommandHandler and TabHandler
        // TODO load template quests into QuestStorage
        // TODO load active quests into QuestStorage
    }

    @Override
    public void onDisable() {
        // TODO halt active tasks
        // TODO save active quests
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] arguments) {
        if (CommandHandler.isQuestCommand(command)) {
            CommandHandler.processQuestCommand(sender);
            return true;
        }
        return false;
    }
}
