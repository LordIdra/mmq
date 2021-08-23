package me.metamechanists.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandHandler {

    private CommandHandler() {}

    public static boolean isQuestCommand(@NotNull Command command) {
        return command.getName().equals("quest");
    }

    public static void processQuestCommand(CommandSender sender) {
        // TODO display category GUI
    }
}
