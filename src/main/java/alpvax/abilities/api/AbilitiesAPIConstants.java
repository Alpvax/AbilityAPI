package alpvax.abilities.api;

import net.minecraft.util.ResourceLocation;

public class AbilitiesAPIConstants
{
	//Mod data
	public static final String MOD_ID = "Abilities";
	public static final String API_ID = MOD_ID + "API";
	
	//Keys
	public static final ResourceLocation ABILITY_PROVIDER_CAPABILITY = new ResourceLocation(MOD_ID.toLowerCase(), "AbilityProviderCapability");
	public static final ResourceLocation ABILITY_AFFECTED_CAPABILITY = new ResourceLocation(MOD_ID.toLowerCase(), "AbilityAffectedCapability");
}
