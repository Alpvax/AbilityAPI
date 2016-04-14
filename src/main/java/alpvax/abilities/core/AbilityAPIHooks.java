package alpvax.abilities.core;


import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.affected.SimpleAbilityAffected;
import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.capabilities.SerializableCapabilityProvider;
import alpvax.abilities.api.effect.EffectInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class AbilityAPIHooks
{
	@SubscribeEvent
	public void onRespawn(PlayerEvent.Clone event)
	{
		if(event.isWasDeath())
		{
			EntityPlayer player = event.getEntityPlayer();
			EntityPlayer original = event.getOriginal();
			if(player.hasCapability(CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY, null) && original.hasCapability(CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY, null))
			{
				IAbilityAffected affected = player.getCapability(CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY, null);
				IAbilityAffected oaffected = original.getCapability(CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY, null);
				for(EffectInstance i : affected.getEffects())
				{
					if(i.persistAcrossDeath())
					{
						oaffected.add(i);
					}
				}
			}
			// TODO:Copy abilities to new EntityPlayer
		}
	}

	@SubscribeEvent
	public void attachCapabilities(AttachCapabilitiesEvent.Entity event)
	{
		event.addCapability(AbilitiesAPIConstants.ABILITY_AFFECTED_CAPABILITY, new SerializableCapabilityProvider.CapabilityProviderAA(new SimpleAbilityAffected(event.getEntity())));
	}

	@SubscribeEvent
	public void onTick(TickEvent.WorldTickEvent event)
	{
		if(event.phase == Phase.END)
		{
			for(Entity e : event.world.loadedEntityList)
			{
				CapabilityAbilityHandler.tickCapability(e);
			}
			for(TileEntity t : event.world.loadedTileEntityList)
			{
				CapabilityAbilityHandler.tickCapability(t);
			}
		}
	}
}
