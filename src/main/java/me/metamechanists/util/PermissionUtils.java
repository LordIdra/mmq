package me.metamechanists.util;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

public class PermissionUtils {

    private static final RegisteredServiceProvider<LuckPerms> luckpermsProvider = Bukkit.getServicesManager()
            .getRegistration(LuckPerms.class);
    private static final LuckPerms luckpermsAPI = luckpermsProvider.getProvider();

    public static boolean addPermission(Player player, Permission permission) {
        try {
            GeneralUtils.plugin.getLogger().info(player.getUniqueId().toString());
            luckpermsAPI.getUserManager().getUser(player.getUniqueId()).data().add(Node.builder(permission.getName()).build());
            luckpermsAPI.getUserManager().saveUser(luckpermsAPI.getUserManager().getUser(player.getUniqueId()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
