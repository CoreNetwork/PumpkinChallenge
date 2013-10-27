package us.corenetwork.pumpkinchallenge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class EventManager {
	private static final SimpleDateFormat startTimeReader;
	static
	{
		startTimeReader = new SimpleDateFormat("MMM dd HH:00", new Locale("En-US"));
		startTimeReader.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	public static int startingTime;
	
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

		int diff = (int) (curTime - startingTime);
		return diff / 3600;
	}
	
	public static boolean isActive()
	{
		long curTime = System.currentTimeMillis() / 1000;
		
		int endingTime = startingTime + Settings.getInt(Setting.EVENT_DURATION) * 3600;
		
		return curTime > startingTime && curTime < endingTime;
	}
	
	public static int getStartingTime()
	{
		String timeString = Settings.getString(Setting.STARTING_TIME);
		int startingTime = -1;
		
		try
		{
			Date date = startTimeReader.parse(timeString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
			
			startingTime = (int) (calendar.getTimeInMillis() / 1000);
		}
		catch (ParseException e)
		{
			PumpkinsPlugin.log.severe("Invalid date formatting! Start date should be in format \"<Month> <Day> <Hour>:00\" !");
		}

		
		return startingTime;
	}
	
	public static void init()
	{
		startingTime = getStartingTime();
		totalDrops = IO.storage.getInt("totalDrops", 0);
		drops = IO.storage.getInt("drops", 0);
		currentCountingHour = IO.storage.getInt("currentCountingHour", 0);
		
		if (!isActive())
		{
			PumpkinsPlugin.log.warning("[PumpkinChallenge] Event is not running at the moment!");
		}
		else
		{
			int endingTime = startingTime + Settings.getInt(Setting.EVENT_DURATION) * 3600;
			double hoursDiff = endingTime - (System.currentTimeMillis() / 1000);
			hoursDiff /= 3600.0;			
			
			PumpkinsPlugin.log.info("Event will end in " + Math.round(hoursDiff * 10.0) / 10.0 + " hours!");
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
