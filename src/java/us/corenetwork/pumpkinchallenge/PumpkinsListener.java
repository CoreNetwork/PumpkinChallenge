package us.corenetwork.pumpkinchallenge;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;


public class PumpkinsListener implements Listener {


	@EventHandler(ignoreCancelled = true)
	public void onEntityDeath(EntityDeathEvent event)
	{
		if (isMobAffected(event.getEntityType()) && EventManager.isActive())
		{			
			EntityEquipment equipment = ((LivingEntity) event.getEntity()).getEquipment();
			ItemStack helmet = equipment.getHelmet();
			if (helmet != null && (helmet.getType() == Material.PUMPKIN || helmet.getType() == Material.JACK_O_LANTERN))
			{
				Material pumpkinType = helmet.getType();
				equipment.setHelmet(null);

				double baseChance = Settings.getDouble(Setting.DROP_CHANCE_BASE);

				EntityDamageEvent lastDamage = event.getEntity().getLastDamageCause();
				if (lastDamage != null && lastDamage instanceof EntityDamageByEntityEvent)
				{
					EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) lastDamage;

					Player killer = null;

					Entity damager = event2.getDamager();
					if (damager instanceof Player)
					{
						killer = (Player) damager;
					}
					else if (damager instanceof Projectile)
					{
						Projectile projectile = (Projectile) damager;
						if (!(projectile.getShooter() instanceof Player))
						{
							return;
						}
					}

					if (killer != null)
					{
						ItemStack hand = killer.getItemInHand();
						if (hand != null)
						{
							int lootingLevel = hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
							if (lootingLevel > 0)
							{
								double multiplier = Settings.getDouble(Setting.DROP_CHANCE_LOOTING_MULTIPLIER);
								baseChance += lootingLevel * multiplier * 0.01;
							}
						}
					}

					if (PumpkinsPlugin.random.nextDouble() < baseChance && EventManager.canDrop())
					{
						ItemStack drop = new ItemStack(pumpkinType, 1);
						event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), drop);
					}
				}
				else
				{
					return;
				}


				return;
			}


		}
	}

	@EventHandler(ignoreCancelled = true)
	public void onEntityCombust(EntityCombustEvent event)
	{
		if (event.getEntity() instanceof LivingEntity && isMobAffected(event.getEntityType()) && EventManager.isActive())
		{
			((LivingEntity) event.getEntity()).setCanPickupItems(false);

			EntityEquipment equipment = ((LivingEntity) event.getEntity()).getEquipment();
			ItemStack helmet = equipment.getHelmet();
			if (helmet != null && (helmet.getType() == Material.PUMPKIN || helmet.getType() == Material.JACK_O_LANTERN))
			{
				event.setCancelled(true);
				return;
			}
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onCreatureSpawn(CreatureSpawnEvent event)
	{
		if (isMobAffected(event.getEntityType()) && EventManager.isActive())
		{	
			if (PumpkinsPlugin.random.nextDouble() < Settings.getDouble(Setting.HELMET_CHANCE_PUMPKIN))
			{
				event.getEntity().getEquipment().setHelmet(new ItemStack(Material.PUMPKIN, 1));
			}
			else if (PumpkinsPlugin.random.nextDouble() < Settings.getDouble(Setting.HELMET_CHANCE_LANTERN))
			{
				event.getEntity().getEquipment().setHelmet(new ItemStack(Material.JACK_O_LANTERN, 1));
			}
		}
	}

	private static boolean isMobAffected(EntityType type)
	{
		List<String> affectedMobs = (List<String>) Settings.getList(Setting.AFFECTED_MOBS);
		return affectedMobs.contains(type.toString());
	}
}
