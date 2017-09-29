package com.openrubicon.core.api.combat;

import com.openrubicon.core.api.cooldowns.CooldownManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class PlayersInCombatManager {

    private static HashMap<Player, PlayerInCombat> playersInCombat = new HashMap<>();

    public static HashMap<Player, PlayerInCombat> getPlayersInCombat() {
        return playersInCombat;
    }

    public static boolean isInCombat(Player player)
    {
        if(!playersInCombat.containsKey(player))
            return false;

        return playersInCombat.get(player).isInCombat();
    }

    public static void addPlayerInCombat(Player player, PlayerInCombatCooldown cooldown)
    {
        playersInCombat.put(player, new PlayerInCombat(player, cooldown));

        CooldownManager.start(playersInCombat.get(player).getCooldown());
    }
}
