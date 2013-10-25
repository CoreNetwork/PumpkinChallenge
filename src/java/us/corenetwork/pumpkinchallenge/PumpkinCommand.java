package us.corenetwork.pumpkinchallenge;

import org.bukkit.command.CommandSender;

public class PumpkinCommand {

	public static void run(CommandSender sender) {
		if (!EventManager.isActive())
		{
			Util.Message(Settings.getString(Setting.MESSAGE_NOT_ACTIVE), sender);
			return;
		}
		
		EventManager.tryAdvanceHour();
		
		String message = Settings.getString(Setting.MESSAGE_PUMPKINS);
		message = message.replace("<HourCollected>", Integer.toString(EventManager.drops));
		message = message.replace("<TotalCollected>", Integer.toString(EventManager.drops + EventManager.totalDrops));

		Util.Message(message, sender);
	}

}
