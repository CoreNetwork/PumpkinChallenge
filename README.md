# PumpkinChallenge

![Three skeletons with pumpkins on their heads](http://i.imgur.com/pC9C3Jkl.png)

[CraftBukkit plugin](http://bukkit.org)  
Supported version: 1.6.4

PumpkinChallenge allows you to turn Halloween (31st October) into a special mob hunting event by:

1. limiting and capping pumpkins spawning on heads of the undead,
2. tracking pumpkin drops and displaying it to players

The event is supposed to be designed for the whole community of your server, where players have to cooperate to get all available pumpkins for current hour, repeating this for 24 cycles (whole day).

## Download

If you don’t want to install from source, you can use our Jenkins site to [download the latest build](http://build.core-network.us:8080/job/PumpkinChallenge/).

## Installation

1. Stop your server
2. Drop PumpkinChallenge.jar into the `plugins` directory
3. Start the server

The plugin will create its `PumpkinChallenge` directory and inside, `config.yml` and `storage.yml`.

## Commands

    /pumpkin

Displays how many pumpkins have been collected during current hour and total.

![Output of the /pumpkin command](http://i.imgur.com/vWW71IP.png)

## Configuration

The plugin is quite configurable and even allows you to turn any day you wish into Halloween.

``` yaml
AffectedMobs:
- ZOMBIE
- SKELETON
- PIGZOMBIE
```

Valid mob names. Please notice that not all mobs can wear items/blocks. The list mimics vanilla Halloween in Minecraft.

``` yaml
ExtraHelmetChance:
  Pumpkin: 0.0
  Lantern: 0.0
```

**Extra** chance that a pumpkin or Jack o’Lantern will spawn on mobs. Please don’t change this if you don’t want more pumpkins spawning on mobs on October 31st. 

If you’re organizing the event on other date, edit it to be something else than `0.0` (default values are `0.25` and `0.025`).

``` yaml
DropChance:
  Base: 0.085
  LootingEnchantMultiplier: 1
```

Chance for a pumpkin to drop with additional bonus when using Looting. You can change it to make it easier or harder for a pumpkin to drop (and reward Looting enchantment more than usual).

``` yaml
CapPerHour: 30
```

Max number of pumpkins or Jack o’Lanterns which can drop every hour. Additional mobs can spawn with pumpkins but they won’t drop more than this number.

```
StartingTime: Oct 31 00:00
EventDurationHours: 24
```

Time management. Change it to current date if you want to test the plugin.

```
Messages:
  Pumpkins: 'This hour: <HourCollected>/30. Total: <TotalCollected>/720'
  NotActive: Event is not active.
```

Messages appearing when players execute the `/pumpkin` command.

## Known bugs

Minecraft will show block-based hats on baby mobs like this:

![Baby zombie with pumpkin appearing higher than its head](http://i.imgur.com/psyjuyL.png)

The plugin cannot change that.