package me.metamechanists.storage;

import me.metamechanists.config.QuestDescriptor;
import org.bukkit.entity.Player;

public record Quest(Player player, QuestDescriptor descriptor) {

    public boolean attemptComplete() {
        if (descriptor.playerHasItems(player)) {
            descriptor.rewardPlayerItems(player);
            descriptor.rewardPlayerPermissions(player);
            return true;
        }
        return false;
    }
}
