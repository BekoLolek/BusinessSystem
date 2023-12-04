package ccrp.businesssystem.commands.SubCommands;

import ccrp.businesssystem.BusinessSystem;
import ccrp.businesssystem.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class ResetConfigDataCommand extends SubCommand {
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
        HashMap<String, Object> defaults = new HashMap<String, Object>();
        JSONObject trainee = new JSONObject();
        trainee.put("name","trainee");
        trainee.put("priority",3);
        trainee.put("payout-percentage", 2);
        defaults.put("trainee",trainee);

        JSONObject employee = new JSONObject();
        employee.put("name","employee");
        employee.put("priority",2);
        employee.put("payout-percentage", 5);
        defaults.put("employee",employee);

        JSONObject assistant = new JSONObject();
        assistant.put("name","assistant");
        assistant.put("priority",1);
        assistant.put("payout-percentage", 8);
        defaults.put("assistant",assistant);

        BusinessSystem.getRankConfig().save(defaults);

        Bukkit.getLogger().info("Config reset successfully!");
    }
}
