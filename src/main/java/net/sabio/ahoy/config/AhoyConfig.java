package net.sabio.ahoy.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = "ahoy")
public class AhoyConfig implements ConfigData {
    @ConfigEntry.Category("ship")
    @ConfigEntry.Gui.Tooltip
    public float shipBaseSpeed = 0.08f;

    @ConfigEntry.Category("ship")
    @ConfigEntry.Gui.Tooltip
    public float shipTurnSpeed = 3.0f;

    @ConfigEntry.Category("ship")
    @ConfigEntry.Gui.Tooltip
    public int shipMaxPassengers = 8;

    @ConfigEntry.Category("ship")
    @ConfigEntry.Gui.Tooltip
    public int shipInventorySlots = 27;

    @ConfigEntry.Category("ship")
    @ConfigEntry.Gui.Tooltip
    public boolean anchorEnabled = true;

    @ConfigEntry.Category("weather")
    @ConfigEntry.Gui.Tooltip
    public boolean stormsEnabled = true;

    @ConfigEntry.Category("weather")
    @ConfigEntry.Gui.Tooltip
    public boolean tsunamisEnabled = true;

    @ConfigEntry.Category("weather")
    @ConfigEntry.Gui.Tooltip
    public int stormChancePerHour = 10;

    @ConfigEntry.Category("combat")
    @ConfigEntry.Gui.Tooltip
    public boolean navalCombatEnabled = true;

    @ConfigEntry.Category("combat")
    @ConfigEntry.Gui.Tooltip
    public int cannonBaseDamage = 20;

    @ConfigEntry.Category("combat")
    @ConfigEntry.Gui.Tooltip
    public int ballistaBaseDamage = 15;

    @ConfigEntry.Category("world")
    @ConfigEntry.Gui.Tooltip
    public boolean underwaterRuinsEnabled = true;

    @ConfigEntry.Category("world")
    @ConfigEntry.Gui.Tooltip
    public int dungeonSpawnHeight = 5;

    private static AhoyConfig instance;

    public static void init() {
        AutoConfig.register(AhoyConfig.class, GsonConfigSerializer::new);
        instance = AutoConfig.getConfigHolder(AhoyConfig.class).getConfig();
    }

    public static AhoyConfig get() {
        if (instance == null) {
            throw new IllegalStateException("[Ahoy] Config not initialized!");
        }
        return instance;
    }
}
