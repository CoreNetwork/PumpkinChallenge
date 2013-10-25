package us.corenetwork.pumpkinchallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import us.corenetwork.pumpkinchallenge.PumpkinsPlugin;
import us.corenetwork.pumpkinchallenge.Setting;
public class IO {
    public static YamlConfiguration config;
    public static YamlConfiguration storage;
    
    public static void LoadSettings()
	{
    	try {
    		config = new YamlConfiguration();
    		storage = new YamlConfiguration();

    		if (!new File(PumpkinsPlugin.instance.getDataFolder(),"config.yml").exists()) 
    			config.save(new File(PumpkinsPlugin.instance.getDataFolder(),"config.yml"));

    		config.load(new File(PumpkinsPlugin.instance.getDataFolder(),"config.yml"));
	    	for (Setting s : Setting.values())
	    	{
	    		if (config.get(s.getString()) == null && s.getDefault() != null) config.set(s.getString(), s.getDefault());
	    	}
	    		    	
    		if (!new File(PumpkinsPlugin.instance.getDataFolder(),"storage.yml").exists()) 
    			storage.save(new File(PumpkinsPlugin.instance.getDataFolder(),"storage.yml"));

    		storage.load(new File(PumpkinsPlugin.instance.getDataFolder(),"storage.yml"));

	    	
	    	saveConfig();
	    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public static void saveStorage()
    {
		try {
			storage.save(new File(PumpkinsPlugin.instance.getDataFolder(),"storage.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    public static void saveConfig()
    {
    	try {
			config.save(new File(PumpkinsPlugin.instance.getDataFolder(),"config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }    	
}
