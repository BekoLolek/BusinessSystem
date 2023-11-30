package ccrp.businesssystem.commands;


import ccrp.businesssystem.commands.SubCommands.HelpCommand;
import ccrp.businesssystem.commands.SubCommands.ReloadCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    public CommandManager() {
        subCommands.add(new ReloadCommand());
        subCommands.add(new HelpCommand());
        //subCommands.add(new Routes());
        //subCommands.add(new MyRoutes());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p =  (Player) commandSender;
            if(strings.length == 0){
                HelpCommand help = new HelpCommand();
                help.perform(p, strings);
            }else if(strings.length >= 1){
                for(int i = 0; i < this.getSubCommands().size();i++){
                    if(strings[0].equalsIgnoreCase(this.getSubCommands().get(i).getName())){
                        this.getSubCommands().get(i).perform(p,strings);

                        return true;
                    }
                }
            }
        }else if(strings[1].equals("reload")){
            ReloadCommand reload = new ReloadCommand();
            reload.performConsole();
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 1){
            ArrayList<String> subCommandArguments = new ArrayList<>();

            for(int i = 0; i < this.getSubCommands().size();i++){
                subCommandArguments.add(subCommands.get(i).getName());
            }
            return subCommandArguments;
        }
        return null;
    }
}
