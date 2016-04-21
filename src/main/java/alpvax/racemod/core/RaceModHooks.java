package alpvax.racemod.core;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RaceModHooks
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onLogin(PlayerLoggedInEvent event)
	{
		final EntityPlayer player = event.player;
		Minecraft.getMinecraft().addScheduledTask(new Runnable()
		{
			@Override
			public void run()
			{
				player.openGui(RaceAndRoleMod.instance, RaceModConstants.RACE_GUI, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
			}
		});
		player.openGui(RaceAndRoleMod.instance, RaceModConstants.ROLE_GUI, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
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
