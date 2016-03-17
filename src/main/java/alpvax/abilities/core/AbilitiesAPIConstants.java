package alpvax.abilities.core;

import net.minecraft.util.ResourceLocation;

public class AbilitiesAPIConstants
{
	// Mod data
	public static final String MOD_NAME = "Ablilties API";


	// Mod data
	public static final String MOD_ID = "Abilities";
	public static final String API_ID = MOD_ID + "API";

	// Keys
	public static final ResourceLocation ABILITY_PROVIDER_CAPABILITY = new ResourceLocation(MOD_ID.toLowerCase(), "AbilityProviderCapability");
	public static final ResourceLocation ABILITY_AFFECTED_CAPABILITY = new ResourceLocation(MOD_ID.toLowerCase(), "AbilityAffectedCapability");

	// NBT Keys
	/** Key for saving ability effects */
	public static final String KEY_ABILITY_ID = "EffectID";

	// ** Key for saving ability effects */
	// public static final String KEY_ABILITY_ID = "EffectID";
}