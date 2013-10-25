package us.corenetwork.pumpkinchallenge;

import us.corenetwork.pumpkinchallenge.IO;
import us.corenetwork.pumpkinchallenge.PumpkinsPlugin;
import us.corenetwork.pumpkinchallenge.Setting;
import us.corenetwork.pumpkinchallenge.Settings;

public class EventManager {
	public static int totalDrops = 0;
	public static int drops = 0;
	public static int currentCountingHour = 0;
	
	public static boolean canDrop()
	{		
		tryAdvanceHour();
		
		int cap = Settings.getInt(Setting.CAP_PER_HOUR);
		drops++;
		
		if (drops > cap)
			drops = cap;
		else if (drops % 5 == 0) //Save every 5 kills
			save();
		
		return drops < cap;
	}
	
	public static void tryAdvanceHour()
	{
		int curHour = getCurrentHour();
		if (currentCountingHour != curHour)
		{
			totalDrops += drops;
			currentCountingHour = curHour;
			drops = 0;
		}
	}
	
	public static int getCurrentHour()
	{
		long curTime = System.currentTimeMillis() / 1000;		
		int startTime = Settings.getInt(Setting.STARTING_TIME);

		int diff = (int) (curTime - startTime);
		return diff / 3600;
	}
	
	public static boolean isActive()
	{
		long curTime = System.currentTimeMillis() / 1000;
		
		int startTime = Settings.getInt(Setting.STARTING_TIME);
		int endingTime = startTime + Settings.getInt(Setting.EVENT_DURATION);
		
		return curTime > startTime && curTime < endingTime;
	}
	
	public static void init()
	{
		totalDrops = IO.storage.getInt("totalDrops", 0);
		drops = IO.storage.getInt("drops", 0);
		currentCountingHour = IO.storage.getInt("currentCountingHour", 0);
		
		if (!isActive())
		{
			PumpkinsPlugin.log.warning("[PumpkinChallenge] Event is not running at the moment!");
		}
	}
	
	public static void save()
	{
		IO.storage.set("totalDrops", totalDrops);
		IO.storage.set("drops", drops);
		IO.storage.set("currentCountingHour", currentCountingHour);
	
		IO.saveStorage();
	}
}
