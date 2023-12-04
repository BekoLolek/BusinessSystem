package ccrp.businesssystem.commands;


import ccrp.businesssystem.commands.SubCommands.CreateBusinessCommand;
import ccrp.businesssystem.commands.SubCommands.HelpCommand;
import ccrp.businesssystem.commands.SubCommands.ReloadCommand;
import ccrp.businesssystem.commands.SubCommands.ResetConfigDataCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new ReloadCommand());
        subCommands.add(new HelpCommand());
        subCommands.add(new CreateBusinessCommand());
        //subCommands.add(new ResetConfigDataCommand()); -> leave comment if you do not want reset to show for players
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (strings.length == 0) {
                HelpCommand help = new HelpCommand();
                help.perform(p, strings);
            } else if (strings.length >= 1) {
                for (int i = 0; i < this.getSubCommands().size(); i++) {
                    if (strings[0].equalsIgnoreCase(this.getSubCommands().get(i).getName())) {
                        this.getSubCommands().get(i).perform(p, strings);
                        return true;
                    }
                }
            }
        } else if (strings[0].equals("reload")) {
            ReloadCommand reload = new ReloadCommand();
            reload.performConsole();
        } else if (strings[0].equals("reset")) {
            ResetConfigDataCommand reset = new ResetConfigDataCommand();
            reset.performConsole();
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            ArrayList<String> subCommandArguments = new ArrayList<>();

            for (int i = 0; i < this.getSubCommands().size(); i++) {
                subCommandArguments.add(subCommands.get(i).getName());
            }
            return subCommandArguments;
        }
        return null;
    }
}
