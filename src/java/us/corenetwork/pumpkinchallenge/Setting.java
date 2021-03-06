package us.corenetwork.pumpkinchallenge;

import java.util.Arrays;


public enum Setting {		
	AFFECTED_MOBS("AffectedMobs", Arrays.asList(new String[] { "ZOMBIE", "SKELETON", "PIGZOMBIE"})),
	
	HELMET_CHANCE_PUMPKIN("HelmetChance.Pumpkin", 0.225),
	HELMET_CHANCE_LANTERN("HelmetChance.Lantern", 0.025),
	
	DROP_CHANCE_BASE("DropChance.Base", 0.085),
	DROP_CHANCE_LOOTING_MULTIPLIER("DropChance.LootingEnchantMultiplier", 1),
	
	CAP_PER_HOUR("CapPerHour", 30),
	STARTING_TIME("StartingTime", "Oct 31 00:00"),
	EVENT_DURATION("EventDurationHours", 24),
	
	MESSAGE_PUMPKINS("Messages.Pumpkins", "This hour: <HourCollected>/<HourlyCap>. Total: <TotalCollected>/<TotalCap>"),
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
