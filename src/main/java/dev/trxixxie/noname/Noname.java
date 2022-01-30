package dev.trxixxie.noname;

import dev.trxixxie.noname.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public final class Noname extends JavaPlugin {

    public Team noname_team;
    public Scoreboard sb;
    public boolean debugmode = false;

    @Override
    public void onEnable() {
        CreateTeam();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @Override
    public void onDisable() {
        noname_team.unregister();
    }

    public void CreateTeam(){
        getLogger().info("Setting up new team!");
        try {
            ScoreboardManager sbm = Bukkit.getScoreboardManager();
            sb = sbm.getMainScoreboard();
            noname_team = sb.registerNewTeam(getName());
            noname_team.setAllowFriendlyFire(true);
            noname_team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
            getLogger().info("Team has been setup!");
        } catch (Exception e){
            e.printStackTrace();
            getLogger().severe("Something went wrong while trying to create the team, please check logs!");
        }
    }
}
