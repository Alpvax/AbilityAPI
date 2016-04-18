package alpvax.abilities.api.capabilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import alpvax.abilities.api.ability.Ability;
import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.affected.SimpleAbilityAffected;
import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.provider.IAbilityProvider;
import alpvax.abilities.api.provider.SimpleAbilityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class CapabilityAbilityHandler
{
	@CapabilityInject(IAbilityAffected.class)
	public static Capability<IAbilityAffected> ABILITY_AFFECTED_CAPABILITY = null;

	@CapabilityInject(IAbilityProvider.class)
	public static Capability<IAbilityProvider> ABILITY_PROVIDER_CAPABILITY = null;

	public static void register()
	{
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
				//TODO:Read
			}
		}, new Callable<IAbilityAffected>()
		{
			@Override
			public IAbilityAffected call() throws Exception
			{
				return new SimpleAbilityAffected(null);
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
				return new SimpleAbilityProvider();
			}
		});
	}

	/**
	 * Ticks the object's IAbilityProvider and IAbilityAffected if they exist.<br>
	 * If the object has an inventory, also loops through and ticks the IAbilityProvider and IAbilityAffected of each
	 * ItemStack if they exist.<br>
	 * <br>
	 * This method ticks the providers for each EnumFacing, as well as no EnumFacing (a facing of null).<br>
	 * <br>
	 * THIS METHOD IS CALLED FOR EACH FACE OF EVERY LOADED ENTITY AND TILEENTITY IN THE WORLD!
	 * @param object the object to attempt to tick
	 */
	public static void tickCapability(ICapabilityProvider object)
	{
		List<ICapabilityTickable> list = new ArrayList<>();
		for(EnumFacing facing : Arrays.copyOf(EnumFacing.VALUES, EnumFacing.VALUES.length + 1))
		{
			if(object.hasCapability(ABILITY_AFFECTED_CAPABILITY, facing))
			{
				IAbilityAffected a = object.getCapability(ABILITY_AFFECTED_CAPABILITY, facing);
				if(!list.contains(a))
				{
					list.add(a);
				}
			}
			if(object.hasCapability(ABILITY_PROVIDER_CAPABILITY, facing))
			{
				IAbilityProvider p = object.getCapability(ABILITY_PROVIDER_CAPABILITY, facing);
				if(!list.contains(p))
				{
					list.add(p);
				}
			}
			if(object.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing))
			{
				IItemHandler h = object.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
				for(int i = 0; i < h.getSlots(); i++)
				{
					ItemStack stack = h.getStackInSlot(i);
					if(stack != null && stack.stackSize > 0)
					{
						tickCapability(stack);
					}
				}
			}
		}
		for(ICapabilityTickable t : list)
		{
			t.tick();
		}
	}

	public static Ability getAbilityByID(UUID id)
	{
		return Registry.INSTANCE.getAbilityByID(id);
	}

	public static IAbilityProvider getProviderByID(UUID id)
	{
		return Registry.INSTANCE.getProviderByID(id);
	}

	public static enum Registry
	{
		INSTANCE;

		private Map<UUID, Ability> idToAbilityMap = new HashMap<>();
		private Map<UUID, IAbilityProvider> idToProviderMap = new HashMap<>();

		public void registerAbility(Ability ability)
		{
			idToAbilityMap.put(ability.getID(), ability);
		}

		public Ability getAbilityByID(UUID id)
		{
			return idToAbilityMap.get(id);
		}

		public void registerProvider(IAbilityProvider provider)
		{
			idToProviderMap.put(provider.getID(), provider);
		}

		public void deleteProvider(IAbilityProvider provider)
		{
			idToProviderMap.remove(provider.getID());
			for(Ability a : provider.getAbilities())
			{
				idToAbilityMap.remove(a.getID());
			}
		}

		public IAbilityProvider getProviderByID(UUID id)
		{
			return idToProviderMap.get(id);
		}
	}
}