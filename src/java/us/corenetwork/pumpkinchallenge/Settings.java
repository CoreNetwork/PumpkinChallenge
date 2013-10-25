package us.corenetwork.pumpkinchallenge;

import java.util.List;


public class Settings {
	
	public static Object getProperty(Setting setting)
	{
		Object property = IO.config.get(setting.getString());
		if (property == null)
		{
			property = setting.getDefault();
		}
		
		return property;
	}

	public static Boolean getBoolean(Setting setting)
	{
		return 	(Boolean) getProperty(setting);
	}
	
	public static Long getLong(Setting setting)
	{
		return 	((Number) getProperty(setting)).longValue();
	}
	
	public static int getInt(Setting setting)
	{
		return ((Number) getProperty(setting)).intValue();
	}
	
	public static double getDouble(Setting setting)
	{
		return ((Number) getProperty(setting)).doubleValue();
	}

	public static String getString(Setting setting)
	{
		return 	(String) getProperty(setting);
	}
		
	public static List<?> getList(Setting setting)
	{
		return 	(List<?>) getProperty(setting);
	}
}
