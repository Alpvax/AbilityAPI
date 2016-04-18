package alpvax.racemod.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RaceModHooks
{
	@SubscribeEvent
	public void onSpawn(EntityJoinWorldEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.getEntity();
			player.openGui(RaceAndRoleMod.instance, RaceModConstants.RACE_GUI, event.getWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
			player.openGui(RaceAndRoleMod.instance, RaceModConstants.ROLE_GUI, event.getWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void attachCapabilities(AttachCapabilitiesEvent.Entity event)
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			if(!event.getEntity().hasCapability(CapabilityRaceHandler.RACE_CAPABILITY, null) && !event.getCapabilities().keySet().contains(RaceModConstants.RACE_CAPABILITY))
			{
				event.addCapability(RaceModConstants.RACE_CAPABILITY, new CapabilityRaceHandler.CapabilityProviderRace(CapabilityRaceHandler.RACE_CAPABILITY.getDefaultInstance().setEntity(event.getEntity())));
			}
			if(!event.getEntity().hasCapability(CapabilityRaceHandler.ROLE_CAPABILITY, null) && !event.getCapabilities().keySet().contains(RaceModConstants.ROLE_CAPABILITY))
			{
				event.addCapability(RaceModConstants.ROLE_CAPABILITY, new CapabilityRaceHandler.CapabilityProviderRole(CapabilityRaceHandler.ROLE_CAPABILITY.getDefaultInstance().setEntity(event.getEntity())));
			}
		}
	}
}
