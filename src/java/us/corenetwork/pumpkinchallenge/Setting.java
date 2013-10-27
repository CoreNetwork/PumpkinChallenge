package us.corenetwork.pumpkinchallenge;

import java.util.Arrays;


public enum Setting {		
	AFFECTED_MOBS("AffectedMobs", Arrays.asList(new String[] { "ZOMBIE", "SKELETON"})),
	
	HELMET_CHANCE_PUMPKIN("HelmetChance.Pumpkin", 0.8),
	HELMET_CHANCE_LANTERN("HelmetChance.Lantern", 0.2),
	
	DROP_CHANCE_BASE("DropChance.Base", 0.085),
	DROP_CHANCE_FORTUNE_MULTIPLIER("DropChance.FortuneMultiplier", 1),
	
	CAP_PER_HOUR("CapPerHour", 30),
	STARTING_DATE("StartingDate", "Oct 27"),
	EVENT_DURATION("EventDurationHours", 3600 * 24),
	
	MESSAGE_PUMPKINS("Messages.Pumpkins", "This hour: <HourCollected>/30. Total: <TotalCollected>/720"),
	MESSAGE_NOT_ACTIVE("Messages.NotActive", "Event is not active.");
	
	private String name;
	private Object def;
	
	private Setting(String Name, Object Def)
	{
		name = Name;
		def = Def;
	}
	
	public String getString()
	{
		return name;
	}
	
	public Object getDefault()
	{
		return def;
	}
}
