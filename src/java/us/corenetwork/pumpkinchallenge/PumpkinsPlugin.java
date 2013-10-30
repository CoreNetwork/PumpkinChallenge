package us.corenetwork.pumpkinchallenge;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public class PumpkinsPlugin extends JavaPlugin {
	public static Logger log = Logger.getLogger("Minecraft");

	private PumpkinsListener listener;
	public static PumpkinsPlugin instance;
	public static Random random;

	@Override
	public void onEnable() {
		instance = this;
		listener = new PumpkinsListener();
		random = new Random();

		IO.LoadSettings();

		getServer().getPluginManager().registerEvents(listener, this);
	
		EventManager.init();
	}



	@Override
	public void onDisable() {
		EventManager.save();
	}



	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (command.getName().equals("pumpkinreload"))
		{
			if (sender.hasPermission("pumpkins.reload"))
			{
				IO.LoadSettings();
				sender.sendMessage("Pumpkins config reloaded.");
			}
		}
		else
		PumpkinCommand.run(sender);
		return true;
	}

}
