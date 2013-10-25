package us.corenetwork.pumpkinchallenge;

import java.util.Arrays;


public enum Setting {		
	AFFECTED_MOBS("AffectedMobs", Arrays.asList(new String[] { "ZOMBIE", "SKELETON"})),
	
	HELMET_CHANCE_LANTERN("LanternChance", 0.2),
	
	CAP_PER_HOUR("CapPerHour", 30),
	STARTING_TIME("StartingTime", 1382652000),
	EVENT_DURATION("EventDuration", 3600 * 24),
	
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
