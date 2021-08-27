package me.metamechanists.commands;

import me.metamechanists.config.CategoryConfig;
import me.metamechanists.gui.CategoryInterface;
import me.metamechanists.util.FileUtils;
import me.metamechanists.util.GeneralUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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
        if (sender instanceof ConsoleCommandSender) {
            GeneralUtils.plugin.getLogger().warning("This command cannot be run from console.");
            return;
        }
        int size = (int) Math.ceil(CategoryConfig.getCategories().size() / 9.0) * 9;
        new CategoryInterface((Player) sender, size, "Categories").display();
    }

    public static void processItemStackSaveCommand(CommandSender sender) {
        if (sender instanceof ConsoleCommandSender) {
            GeneralUtils.plugin.getLogger().warning("This command cannot be run from console.");
            return;
        }

        Player player = (Player) sender;
        ItemStack stack = player.getInventory().getItemInMainHand();
        FileUtils.writeStack(player, stack);
    }
}
