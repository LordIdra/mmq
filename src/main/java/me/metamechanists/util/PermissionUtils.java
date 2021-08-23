package me.metamechanists.util;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

public class PermissionUtils {

    private static final RegisteredServiceProvider<LuckPerms> luckpermsProvider = Bukkit.getServicesManager()
            .getRegistration(LuckPerms.class);
    private static final LuckPerms luckpermsAPI = luckpermsProvider.getProvider();

    public static void addPermission(Player player, Permission permission) {
        luckpermsAPI.getUserManager().getUser(player.getUniqueId()).data()
                .add(Node.builder(permission.getName()).build());
    }
}
