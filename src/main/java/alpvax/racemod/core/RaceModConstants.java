package alpvax.racemod.core;

import net.minecraft.util.ResourceLocation;

public class RaceModConstants
{
	// Mod data
	public static final String MOD_ID = "races&roles";
	public static final String MOD_NAME = "Races and Roles";

	//GUI ids
	private static int nextGUIID = 0;
	public static final int RACE_GUI = nextGUIID++;
	public static final int ROLE_GUI = nextGUIID++;

	// Keys
	public static final ResourceLocation RACE_CAPABILITY = new ResourceLocation(MOD_ID.toLowerCase(), "CapabilityRace");
	public static final ResourceLocation ROLE_CAPABILITY = new ResourceLocation(MOD_ID.toLowerCase(), "CapabilityRole");
}
