package alpvax.abilities.core;


import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.effect.EffectInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
				for(EffectInstance i : affected.getEffects().values())
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
		//TODO:event.addCapability(AbilitiesAPIConstants.ABILITY_AFFECTED_CAPABILITY, new AbilityAffectedProvider.Entity(event.getEntity()));
	}
}
