package alpvax.abilities.core;

import alpvax.abilities.affected.AbilityAffectedProvider;
import alpvax.abilities.api.IAbilityHandler;
import alpvax.abilities.api.IAbilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AbilityAPIHooks
{
	@SubscribeEvent
	public void onRespawn(PlayerEvent.Clone event)
	{
		if(event.wasDeath && event.original.hasCapability(CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY, null))
		{
			IAbilityHandler old = event.original.getCapability(CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY, null);
			IAbilityHandler newHandler = event.entityPlayer.getCapability(CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY, null);
			for(IAbilityProvider p : old.getProviders())
			{
				newHandler.addAbility(p);
			}
		}
	}
	
	@SubscribeEvent
	public void attachCapabilities(AttachCapabilitiesEvent.Entity event)
	{
		event.addCapability(AbilitiesAPIConstants.ABILITY_AFFECTED_CAPABILITY, new AbilityAffectedProvider.Entity(event.getEntity()));
	}
}
