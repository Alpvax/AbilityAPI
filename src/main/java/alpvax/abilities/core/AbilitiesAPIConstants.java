package alpvax.abilities.core;

import net.minecraft.util.ResourceLocation;

public class AbilitiesAPIConstants
{


	// Mod data
	public static final String MOD_ID = "abilities";
	public static final String API_ID = MOD_ID + "|API";

	public static final String MOD_NAME = "Ablilties API";

	// Keys
	public static final ResourceLocation ABILITY_HANDLER_CAPABILITY = new ResourceLocation(MOD_ID.toLowerCase(), "AbilityHandlerCapability");
	public static final ResourceLocation ABILITY_PROVIDER_CAPABILITY = new ResourceLocation(MOD_ID.toLowerCase(), "AbilityProviderCapability");
	public static final ResourceLocation ABILITY_AFFECTED_CAPABILITY = new ResourceLocation(MOD_ID.toLowerCase(), "AbilityAffectedCapability");
	public static final ResourceLocation SKILL_HANDLER_CAPABILITY = new ResourceLocation(MOD_ID.toLowerCase(), "SkillHandlerCapability");


	public static final String KEY_EFFECT_ID = "EffectID";
	public static final String KEY_ID_MOST = "UUIDMost";
	public static final String KEY_ID_LEAST = "UUIDLeast";
	public static final String KEY_ABILITIES = "AbilitiesList";
	public static final String KEY_SKILL_ID = "SkillID";
	public static final String KEY_SKILL_BASE = "SkillBaseValue";
	public static final String KEY_SKILL_MILESTONES = "SkillMilestones";
	public static final String KEY_SKILL_MODIFIERS = "SkillModifiers";
	public static final String KEY_MODIFIER_TYPE = "ModifierOperation";
	public static final String KEY_MODIFIER_VALUE = "ModifyAmount";
	public static final String KEY_INSTANCE_CLASS = "InstanceClass";
}