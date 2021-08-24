package me.metamechanists.commands;

import me.metamechanists.util.FileUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandHandler {

    private CommandHandler() {}

    public static boolean isQuestCommand(@NotNull Command command) {
        return command.getName().equals("quest");
    }

    public static boolean isItemStackSaveCommand(@NotNull Command command) {
        return command.getName().equalsIgnoreCase("itemstacksave");
    }

    public static void processQuestCommand(CommandSender sender) {
        // TODO display category GUI
    }

    public static void processItemStackSaveCommand(CommandSender sender) {
        Player player = (Player) sender;
        ItemStack stack = player.getItemInUse();
        FileUtils.writeStack(player, stack);
    }
}
