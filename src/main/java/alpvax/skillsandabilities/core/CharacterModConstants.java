package alpvax.skillsandabilities.core;

import net.minecraft.util.ResourceLocation;

public class CharacterModConstants
{
	//Mod Data
	public static final String MOD_ID = "skillsandabilities";
	public static final String MOD_VERSION = "0.1.0";

	//NBT Keys
	//public static final String KEY_EFFECT_ID = "EffectID";
	public static final String KEY_ID_MOST = "UUIDMost";
	public static final String KEY_ID_LEAST = "UUIDLeast";
	//public static final String KEY_ABILITIES = "AbilitiesList";
	public static final String KEY_SKILL_ID = "SkillID";
	//public static final String KEY_SKILL_BASE = "SkillBaseValue";
	//public static final String KEY_SKILL_MILESTONES = "SkillMilestones";
	public static final String KEY_SKILL_MODIFIERS = "SkillModifiers";
	public static final String KEY_MODIFIER_TYPE = "ModifierOperation";
	public static final String KEY_MODIFIER_VALUE = "ModifyAmount";

	public static final String KEY_INSTANCE_CLASS = "InstanceClass";

	//Capability keys
	public static final ResourceLocation CAPABILITY_ABILITY_TARGET = new ResourceLocation(MOD_ID, "CapabilityAbilityTarget");
}
