package event;

import core.logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static handler.player.check;
import static main.main.ShowPlayerInfo;

public class eventlistener implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){
        Player player=event.getPlayer();
        if(ShowPlayerInfo){
            logger.log("INFO","BanSystem_PlayerJoinEvent","Player >> "+player.getName()+" | UUID >> "+player.getUniqueId()+" | IP >> "+player.getAddress());
        }
        check();
    }
}
