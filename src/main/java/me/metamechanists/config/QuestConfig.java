package me.metamechanists.config;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class QuestConfig {

    private static final Map<String, QuestDescriptor> questMap = new HashMap<>();

    private QuestConfig() {}

    public static void load() {
        // TODO load quests.yml into questMap
    }

    public static QuestDescriptor getQuest(String id) {
        if (!questMap.containsKey(id)) {
            Bukkit.getServer().getLogger().severe("Quest with ID " + id + " not found");
        }
        return questMap.get(id);
    }
}
