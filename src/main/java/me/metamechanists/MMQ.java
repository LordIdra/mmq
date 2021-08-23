package me.metamechanists;

import org.bukkit.plugin.java.JavaPlugin;

public class MMQ extends JavaPlugin {

    @Override
    public void onEnable() {
        // TODO
        // load MessageConfig
        // load CategoryConfig
        // load QuestConfig
        // set CommandHandler
        // load template quests into QuestStorage
        // load active quests into QuestStorage
    }

    @Override
    public void onDisable() {
        // halt active tasks
        // save active quests
    }
}
