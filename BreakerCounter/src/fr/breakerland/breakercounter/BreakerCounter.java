package fr.breakerland.breakercounter;

import java.util.Iterator;


import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;


public class BreakerCounter extends JavaPlugin implements CommandExecutor {
	@Override
	public void onEnable() {
		saveDefaultConfig();
		getCommand("advc").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}
		Integer done = 0;
		Integer count= 0;
		Player p = (Player) sender;
		Iterator<Advancement> it = getServer().advancementIterator();
		while(it.hasNext()) {
			Advancement a = it.next();
			AdvancementProgress progress = p.getAdvancementProgress(a);
			if(progress.isDone()) {
				done++;
			}
			count++;
		}
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("message", "&aVous avez complété &6%done%/%count% &aachievements !").replace("%done%", done.toString()).replace("%count%", count.toString())));
		
		return true;
	}
	

}
