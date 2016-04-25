package alpvax.abilities.api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.handler.IAbilityHandler;
import alpvax.abilities.api.provider.IAbilityProvider;
import alpvax.abilities.core.AbilitiesAPIConstants;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ProviderList implements Iterable<IAbilityProvider>
{
	private final IAbilityHandler handler;

	private List<UUID> attachedProviders = new ArrayList<>();
	private Map<UUID, IAbilityProvider> providers = new HashMap<>();

	public ProviderList(IAbilityHandler handler)
	{
		this.handler = handler;
	}

	public void update()
	{
		List<IAbilityProvider> list = new ArrayList<>();
		for(EnumFacing facing : Arrays.copyOf(EnumFacing.VALUES, EnumFacing.VALUES.length + 1))
		{
			for(IAbilityProvider p : getProviders(handler.getHandled(), facing))
			{
				if(!list.contains(p))
				{
					list.add(p);
				}
			}
		}
		for(Iterator<IAbilityProvider> i = iterator(); i.hasNext();)
		{
			IAbilityProvider p = i.next();
			if(!attachedProviders.contains(p.getKey()))
			{
				if(!list.remove(p))
				{
					i.remove();
				}
			}
		}
		addAll(list);
	}

	private List<IAbilityProvider> getProviders(ICapabilityProvider object, EnumFacing facing)
	{
		List<IAbilityProvider> list = new ArrayList<>();
		if(object.hasCapability(CapabilityAbilityHandler.ABILITY_PROVIDER_CAPABILITY, facing))
		{
			list.add(object.getCapability(CapabilityAbilityHandler.ABILITY_PROVIDER_CAPABILITY, facing));
		}
		if(object.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing))
		{
			IItemHandler h = object.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
			for(int i = 0; i < h.getSlots(); i++)
			{
				ItemStack stack = h.getStackInSlot(i);
				if(stack != null && stack.stackSize > 0)
				{
					list.addAll(getProviders(stack, facing));
				}
			}
		}
		return list;
	}

	public List<IAbilityProvider> attached()
	{
		List<IAbilityProvider> list = new ArrayList<>();
		for(UUID id : attachedProviders)
		{
			list.add(providers.get(id));
		}
		return list;
	}

	public List<IAbilityProvider> asList()
	{
		return new ArrayList<>(providers.values());
	}

	public int size()
	{
		return attachedProviders.size() + providers.size();
	}

	public boolean isEmpty()
	{
		return size() == 0;
	}

	public boolean contains(IAbilityProvider p)
	{
		return providers.containsKey(p.getAttachKey());
	}

	@Override
	public Iterator<IAbilityProvider> iterator()
	{
		return providers.values().iterator();
	}

	public void grantAbilities(IAbilityProvider provider)
	{
		attachedProviders.add(provider.getKey());
		add(provider);
	}

	private void add(IAbilityProvider p)
	{
		providers.put(p.getKey(), p);
	}

	private void addAll(Iterable<? extends IAbilityProvider> i)
	{
		for(IAbilityProvider p : i)
		{
			add(p);
		}
	}

	public IAbilityProvider get(UUID key)
	{
		return providers.get(key);
	}

	protected IAbilityProvider remove(UUID key)
	{
		return providers.remove(key);
	}

	public void readEntryFromNBT(NBTTagCompound nbt)
	{
		if(nbt.hasKey(AbilitiesAPIConstants.KEY_ID_MOST, NBT.TAG_LONG) && nbt.hasKey(AbilitiesAPIConstants.KEY_ID_LEAST, NBT.TAG_LONG))
		{
			UUID id = new UUID(nbt.getLong(AbilitiesAPIConstants.KEY_ID_MOST), nbt.getLong(AbilitiesAPIConstants.KEY_ID_MOST));
			get(id).deserializeNBT(nbt.getTagList(AbilitiesAPIConstants.KEY_ABILITIES, NBT.TAG_COMPOUND));
		}
	}

	public NBTTagCompound writeEntryToNBT(UUID id)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		IAbilityProvider p = get(id);
		if(p != null)
		{
			nbt.setLong(AbilitiesAPIConstants.KEY_ID_MOST, id.getMostSignificantBits());
			nbt.setLong(AbilitiesAPIConstants.KEY_ID_LEAST, id.getLeastSignificantBits());
			nbt.setTag(AbilitiesAPIConstants.KEY_ABILITIES, p.serializeNBT());
		}
		return nbt;
	}
}
