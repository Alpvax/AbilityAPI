package alpvax.abilities.core;


import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.affected.SimpleAbilityAffected;
import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.capabilities.SerializableCapabilityProvider;
import alpvax.abilities.api.capabilities.SimpleCapabilityProvider;
import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.handler.EntityAbilityHandler.PlayerAbilityHandler;
import alpvax.abilities.api.handler.IAbilityHandler;
import alpvax.abilities.api.provider.IAbilityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
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
				for(EffectInstance i : oaffected.getEffects())
				{
					if(i.persistAcrossDeath())
					{
						affected.add(i);
					}
				}
			}
			if(player.hasCapability(CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY, null) && original.hasCapability(CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY, null))
			{
				IAbilityHandler h = player.getCapability(CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY, null);
				IAbilityHandler oh = original.getCapability(CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY, null);
				for(IAbilityProvider p : oh.getAttachedProviders())
				{
					IAbilityProvider p1 = p.cloneAcrossDeath();
					if(p1 != null)
					{
						h.grantPowers(p1);
					}
				}
			}
			// TODO:Copy abilities to new EntityPlayer
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void attachCapabilities(AttachCapabilitiesEvent.Entity event)
	{
		if(!event.getEntity().hasCapability(CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY, null) && !event.getCapabilities().keySet().contains(AbilitiesAPIConstants.ABILITY_AFFECTED_CAPABILITY))
		{
			event.addCapability(AbilitiesAPIConstants.ABILITY_AFFECTED_CAPABILITY, new SerializableCapabilityProvider.CapabilityProviderAA(new SimpleAbilityAffected(event.getEntity())));
		}
		if(event.getEntity() instanceof EntityPlayer)
		{
			event.addCapability(AbilitiesAPIConstants.ABILITY_HANDLER_CAPABILITY, new SimpleCapabilityProvider.CapabilityProviderAH(new PlayerAbilityHandler(event.getEntity())));
		}
	}

	@SubscribeEvent
	public void onTick(TickEvent.ServerTickEvent event)
	{
		if(event.phase == Phase.END)
		{
			for(IAbilityHandler h : CapabilityAbilityHandler.allHandlers())
			{
				h.updateProviderList();
			}
			for(IAbilityProvider p : CapabilityAbilityHandler.allProviders())
			{
				p.tick();
			}
			for(IAbilityAffected a : CapabilityAbilityHandler.allAffected())
			{
				a.tick();
			}
		}
	}
}
