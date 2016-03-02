package alpvax.abilities.core;

import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AbilityModHooks
{
	@SubscribeEvent
	public void onRespawn(PlayerEvent.Clone event)
	{
		if(event.wasDeath)
		{
			//TODO:Copy data to new EntityPlayer
		}
	}
	
	@SubscribeEvent
	public void attachCapabilities(AttachCapabilitiesEvent.Entity event)
	{
		//TODO:event.addCapability(AbilitiesModConstants.ABILITY_AFFECTED_CAPABILITY, new AbilityCapabilityProvider<Entity>());
	}
}
