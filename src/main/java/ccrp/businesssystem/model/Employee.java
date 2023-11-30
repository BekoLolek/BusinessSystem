package ccrp.businesssystem.model;

import org.bukkit.OfflinePlayer;

public class Employee {
    public OfflinePlayer player;
    public int rank;
    public boolean isOwner;

    public Employee(OfflinePlayer player, boolean isOwner) {
        this.player = player;
        this.rank = 0;
        this.isOwner = isOwner;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public void setPlayer(OfflinePlayer player) {
        this.player = player;
    }

    public String getRank() {
        return Business.RANKS.get(rank);
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }
}
