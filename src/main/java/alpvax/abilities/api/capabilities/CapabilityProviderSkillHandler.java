package alpvax.abilities.api.capabilities;

import alpvax.abilities.api.skill.ISkillHandler;
import net.minecraft.nbt.NBTTagList;

public class CapabilityProviderSkillHandler extends SerializableCapabilityProvider<ISkillHandler, NBTTagList>
{
	public CapabilityProviderSkillHandler()
	{
		super(CapabilityAbilityHandler.SKILL_HANDLER_CAPABILITY.getDefaultInstance(), CapabilityAbilityHandler.SKILL_HANDLER_CAPABILITY);
	}
}
