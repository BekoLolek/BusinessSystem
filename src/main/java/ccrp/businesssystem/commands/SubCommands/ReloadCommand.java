package ccrp.businesssystem.commands.SubCommands;

import ccrp.businesssystem.BusinessSystem;
import ccrp.businesssystem.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getSyntax() {
        return null;
    }

    @Override
    public void perform(Player player, String[] args) {

    }

    public void performConsole(){
        BusinessSystem.getPlugin().reloadConfig();
        Bukkit.getLogger().info("Config reloaded successfully!");
    }
}
