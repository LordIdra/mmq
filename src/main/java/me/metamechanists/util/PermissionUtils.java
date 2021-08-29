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
            final UserManager userManager = luckpermsAPI.getUserManager();
            User user = userManager.getUser(player.getUniqueId());
            user.data().add(Node.builder(permission.getName()).value(true).build());
            userManager.saveUser(user);
        } catch (Exception e) {
            GeneralUtils.plugin.getLogger().info("we're moving to south africa");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
