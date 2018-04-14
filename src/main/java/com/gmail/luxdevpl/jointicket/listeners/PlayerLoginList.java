package com.gmail.luxdevpl.jointicket.listeners;

import com.gmail.luxdevpl.jointicket.MainClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.List;

public class PlayerLoginList implements Listener {

    @EventHandler
    public void handle(PlayerLoginEvent e) {
        if (!MainClass.getInstance().getTicketManager().hasAuthorizationTicket(e.getAddress().getHostAddress())) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, this.getKickMessage());
            return;
        }
        MainClass.getInstance().getTicketManager().removeTicket(e.getAddress().getHostAddress());
    }

    private String getKickMessage(){
        List<String> kickMessageList = MainClass.getInstance().getConfiguration().configuration.kickMessage;
        StringBuilder kickMessage = new StringBuilder();

        for (int i = 0; i < kickMessageList.size(); i++) {
            kickMessage.append(kickMessageList.get(i));
            if (i <= kickMessageList.size() - 2) {
                kickMessage.append("\n");
            }
        }
        return ChatColor.translateAlternateColorCodes('&', kickMessage.toString());
    }

}
