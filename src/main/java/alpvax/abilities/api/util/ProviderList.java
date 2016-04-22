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
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ProviderList implements Iterable<IAbilityProvider>
{
	private final IAbilityHandler handler;

	private Map<UUID, IAbilityProvider> attachedProviders = new HashMap<>();
	private List<IAbilityProvider> providers = new ArrayList<>();

	public ProviderList(IAbilityHandler handler)
	{
		this.handler = handler;
	}

	public void update()
	{
		providers.clear();
		for(EnumFacing facing : Arrays.copyOf(EnumFacing.VALUES, EnumFacing.VALUES.length + 1))
		{
			for(IAbilityProvider p : getProviders(handler.getHandled(), facing))
			{
				if(!providers.contains(p))
				{
					providers.add(p);
				}
			}
		}
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
		return new ArrayList<>(attachedProviders.values());
	}

	public List<IAbilityProvider> asList()
	{
		List<IAbilityProvider> list = attached();
		list.addAll(providers);
		return list;
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
		return providers.contains(p) || attachedProviders.containsKey(p.getKey());
	}

	@Override
	public Iterator<IAbilityProvider> iterator()
	{
		return new JoinedIter();
	}

	public void grant(IAbilityProvider provider)
	{
		attachedProviders.put(provider.getKey(), provider);
	}

	public boolean add(IAbilityProvider e)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(Object o)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(Iterable<? extends IAbilityProvider> i)
	{
		boolean flag = false;
		for(IAbilityProvider p : i)
		{
			flag |= add(p);
		}
		return flag;
	}

	public boolean removeAll(Iterable<? extends IAbilityProvider> i)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void clear()
	{
		// TODO Auto-generated method stub

	}

	public IAbilityProvider get(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public IAbilityProvider remove(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private class JoinedIter implements Iterator<IAbilityProvider>
	{
		Iterator<IAbilityProvider> ai = attachedProviders.values().iterator();
		Iterator<IAbilityProvider> pi = providers.iterator();

		@Override
		public boolean hasNext()
		{
			return ai.hasNext() || pi.hasNext();
		}

		@Override
		public IAbilityProvider next()
		{
			return ai.hasNext() ? ai.next() : pi.next();
		}
	}
}
