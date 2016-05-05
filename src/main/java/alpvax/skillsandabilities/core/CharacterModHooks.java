package alpvax.skillsandabilities.core;

import alpvax.skillsandabilities.capabilities.CapabilityCharacterHandler;
import alpvax.skillsandabilities.capabilities.SerializableCapabilityProvider;
import alpvax.skillsandabilities.target.IAbilityTarget;
import alpvax.skillsandabilities.target.SimpleAbilityTarget;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CharacterModHooks
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void attachCapabilities(AttachCapabilitiesEvent.Entity event)
	{
		if(!event.getEntity().hasCapability(CapabilityCharacterHandler.CAPABILITY_ABILITY_TARGET, null) && !event.getCapabilities().keySet().contains(CharacterModConstants.CAPABILITY_ABILITY_TARGET))
		{
			event.addCapability(CharacterModConstants.CAPABILITY_ABILITY_TARGET, new SerializableCapabilityProvider<IAbilityTarget, NBTTagCompound>(new SimpleAbilityTarget(event.getEntity()), CapabilityCharacterHandler.CAPABILITY_ABILITY_TARGET));
		}
		/*if(event.getEntity() instanceof EntityPlayer)
		{
			event.addCapability(AbilitiesAPIConstants.ABILITY_HANDLER_CAPABILITY, new SimpleCapabilityProvider.CapabilityProviderAH(new PlayerAbilityHandler(event.getEntity())));
		}
		if(event.getEntity() instanceof EntityPlayer && !event.getEntity().hasCapability(CapabilityAbilityHandler.SKILL_HANDLER_CAPABILITY, null) && !event.getCapabilities().keySet().contains(AbilitiesAPIConstants.SKILL_HANDLER_CAPABILITY))
		{
			event.addCapability(AbilitiesAPIConstants.SKILL_HANDLER_CAPABILITY, new CapabilityProviderSkillHandler());
		}*/
	}
}
