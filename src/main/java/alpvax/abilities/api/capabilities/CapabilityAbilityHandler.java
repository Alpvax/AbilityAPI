package alpvax.abilities.api.capabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.affected.SimpleAbilityAffected;
import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.handler.IAbilityHandler;
import alpvax.abilities.api.handler.SimpleAbilityHandler;
import alpvax.abilities.api.provider.IAbilityProvider;
import alpvax.abilities.api.provider.SimpleAbilityProvider;
import alpvax.abilities.api.skill.ISkillHandler;
import alpvax.abilities.api.skill.SimpleSkillHandler;
import alpvax.abilities.api.util.IKeyedEntry;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityAbilityHandler
{
	@CapabilityInject(IAbilityHandler.class)
	public static Capability<IAbilityHandler> ABILITY_HANDLER_CAPABILITY = null;

	@CapabilityInject(IAbilityProvider.class)
	public static Capability<IAbilityProvider> ABILITY_PROVIDER_CAPABILITY = null;

	@CapabilityInject(IAbilityAffected.class)
	public static Capability<IAbilityAffected> ABILITY_AFFECTED_CAPABILITY = null;

	@CapabilityInject(ISkillHandler.class)
	public static Capability<ISkillHandler> SKILL_HANDLER_CAPABILITY = null;

	public static void registerCapabilities()
	{
		CapabilityManager.INSTANCE.register(IAbilityHandler.class, new Capability.IStorage<IAbilityHandler>()
		{
			@Override
			public NBTBase writeNBT(Capability<IAbilityHandler> capability, IAbilityHandler instance, EnumFacing side)
			{
				return null;
			}

			@Override
			public void readNBT(Capability<IAbilityHandler> capability, IAbilityHandler instance, EnumFacing side, NBTBase base)
			{
			}
		}, new Callable<IAbilityHandler>()
		{
			@Override
			public IAbilityHandler call() throws Exception
			{
				return new SimpleAbilityHandler(null);
			}
		});
		CapabilityManager.INSTANCE.register(IAbilityProvider.class, new Capability.IStorage<IAbilityProvider>()
		{
			@Override
			public NBTBase writeNBT(Capability<IAbilityProvider> capability, IAbilityProvider instance, EnumFacing side)
			{
				return null;
			}

			@Override
			public void readNBT(Capability<IAbilityProvider> capability, IAbilityProvider instance, EnumFacing side, NBTBase base)
			{

			}
		}, new Callable<IAbilityProvider>()
		{
			@Override
			public IAbilityProvider call() throws Exception
			{
				return new SimpleAbilityProvider(null);
			}
		});
		CapabilityManager.INSTANCE.register(IAbilityAffected.class, new Capability.IStorage<IAbilityAffected>()
		{
			@Override
			public NBTBase writeNBT(Capability<IAbilityAffected> capability, IAbilityAffected instance, EnumFacing side)
			{
				NBTTagList list = new NBTTagList();
				for(EffectInstance e : instance.getEffects())
				{
					list.appendTag(e.serializeNBT());
				}
				return list;
			}

			@Override
			public void readNBT(Capability<IAbilityAffected> capability, IAbilityAffected instance, EnumFacing side, NBTBase base)
			{
				NBTTagList list = (NBTTagList)base;
				int i = 0;
				for(EffectInstance e : instance.getEffects())
				{//TODO check order
					e.deserializeNBT(list.getCompoundTagAt(i++));
				}
			}
		}, new Callable<IAbilityAffected>()
		{
			@Override
			public IAbilityAffected call() throws Exception
			{
				return new SimpleAbilityAffected(null);
			}
		});
		CapabilityManager.INSTANCE.register(ISkillHandler.class, new Capability.IStorage<ISkillHandler>()
		{
			@Override
			public NBTBase writeNBT(Capability<ISkillHandler> capability, ISkillHandler instance, EnumFacing side)
			{
				return null;
			}

			@Override
			public void readNBT(Capability<ISkillHandler> capability, ISkillHandler instance, EnumFacing side, NBTBase base)
			{

			}
		}, new Callable<ISkillHandler>()
		{
			@Override
			public ISkillHandler call() throws Exception
			{
				return new SimpleSkillHandler();
			}
		});
	}

	private static Map<UUID, IAbilityHandler> all_handlers = new HashMap<>();
	private static Map<UUID, IAbilityProvider> all_providers = new HashMap<>();
	private static Map<UUID, IAbilityAffected> all_affected = new HashMap<>();

	public static <T extends IKeyedEntry<UUID>> T register(T keyedCap)
	{
		if(keyedCap instanceof IAbilityHandler)
		{
			all_handlers.put(keyedCap.getKey(), (IAbilityHandler)keyedCap);
		}
		if(keyedCap instanceof IAbilityProvider)
		{
			all_providers.put(keyedCap.getKey(), (IAbilityProvider)keyedCap);
		}
		if(keyedCap instanceof IAbilityAffected)
		{
			all_affected.put(keyedCap.getKey(), (IAbilityAffected)keyedCap);
		}
		return keyedCap;
	}

	public static List<IAbilityHandler> allHandlers()
	{
		return new ArrayList<>(all_handlers.values());
	}

	public static IAbilityHandler getHandler(UUID id)
	{
		return all_handlers.get(id);
	}

	public static List<IAbilityProvider> allProviders()
	{
		return new ArrayList<>(all_providers.values());
	}

	public static IAbilityProvider getProvider(UUID id)
	{
		return all_providers.get(id);
	}

	public static List<IAbilityAffected> allAffected()
	{
		return new ArrayList<>(all_affected.values());
	}

	public static IAbilityAffected getAffected(UUID id)
	{
		return all_affected.get(id);
	}
}