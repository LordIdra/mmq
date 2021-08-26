package me.metamechanists.gui;

import java.util.Arrays;

import me.metamechanists.util.GeneralUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class UserInterface implements Listener {

    private final Inventory inventory;
    protected final Player player;

    protected UserInterface (Player player, int size, String title) {
        this.player = player;
        this.inventory = Bukkit.createInventory(null, size, title);
        GeneralUtils.plugin.getServer().getPluginManager().registerEvents(this, GeneralUtils.plugin);
    }

    public void display() {
        player.openInventory(inventory);
    }

    protected void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }

    protected void setItem(int slot, Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return;
        }
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        inventory.setItem(slot, item);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() != inventory)
            return;
        event.setCancelled(true);
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir())
            return;
        onClick(player, event.getRawSlot(), event.getClick());
    }

    @EventHandler
    public void onInventoryClick(InventoryDragEvent event) {
        if (event.getInventory().equals(inventory))
            event.setCancelled(true);
    }

    protected abstract void onClick(Player player, int slot, ClickType click);
}