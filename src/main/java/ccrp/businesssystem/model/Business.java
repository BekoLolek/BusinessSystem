package ccrp.businesssystem.model;

import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Business {
    private int id = 0;
    private int ownerPayout = 0;
    private int employeePayout = 0;
    private int businessPayout = 0;

    ArrayList<Employee> employees = new ArrayList<>();

    public static final List<String> RANKS = Arrays.asList("trainee","employee","assistant");

    public void promote(OfflinePlayer player) {
        if(isEligible(player)){
            int rankId = employees.stream().filter(obj -> obj.player.equals(player)).findFirst().get().rank;
            try{
                String newRank = RANKS.get(rankId + 1);
                employees.stream().filter(obj -> obj.player.equals(player)).findFirst().get().setRank(rankId + 1);
            }catch (Exception e){
                //player cannot be promoted any more
            }
        }
    }

    public void demote(OfflinePlayer player) {
        if(isEligible(player)){
            int rankId = employees.stream().filter(obj -> obj.player.equals(player)).findFirst().get().rank;
            try{
                String newRank = RANKS.get(rankId - 1);
                employees.stream().filter(obj -> obj.player.equals(player)).findFirst().get().setRank(rankId - 1);
            }catch (Exception e){
                //player cannot be demoted any more
            }
        }
    }

    public void hire(OfflinePlayer player) {
        if(!isEligible(player)){
            Employee emp = new Employee(player,false);
            employees.add(emp);
        }
    }

    public void fire(OfflinePlayer player) {
        if(isEligible(player)){
            employees.remove(employees.stream().filter(obj -> obj.player.equals(player)).findFirst());
        }
    }

    public void handleIncome(int amount) {

    }

    private boolean isEligible(OfflinePlayer player){
        boolean eligible = true;
        for( Employee e : employees){
            if(e.player.equals(player)){
                eligible = false;
                break;
            }
        }

        return eligible;
    }


}
