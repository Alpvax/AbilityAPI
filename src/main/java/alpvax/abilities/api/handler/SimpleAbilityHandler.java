package alpvax.abilities.api.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.provider.IAbilityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class SimpleAbilityHandler implements IAbilityHandler
{
	public static List<IAbilityProvider> getProviders(ICapabilityProvider object, EnumFacing facing)
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

	private final ICapabilityProvider handled;
	private UUID id = UUID.randomUUID();
	private Map<String, IAbilityProvider> attachedProviders = new HashMap<>();
	private List<IAbilityProvider> providers = new ArrayList<>();

	public SimpleAbilityHandler(ICapabilityProvider handled)
	{
		this.handled = handled;
		CapabilityAbilityHandler.register(this);
	}

	@Override
	public ICapabilityProvider getHandled()
	{
		return handled;
	}

	@Override
	public UUID getKey()
	{
		return id;
	}

	@Override
	public void updateProviderList()
	{
		providers.clear();
		for(EnumFacing facing : Arrays.copyOf(EnumFacing.VALUES, EnumFacing.VALUES.length + 1))
		{
			for(IAbilityProvider p : getProviders(getHandled(), facing))
			{
				if(!providers.contains(p))
				{
					providers.add(p);
				}
			}
		}
	}

	@Override
	public List<IAbilityProvider> getProviders()
	{
		List<IAbilityProvider> list = new ArrayList<>(attachedProviders.values());
		list.addAll(providers);
		return list;
	}

	@Override
	public void grantPowers(IAbilityProvider provider)
	{
		if(!attachedProviders.containsKey(provider.getAttachKey()))
		{
			attachedProviders.put(provider.getAttachKey(), provider);
		}
	}

	@Override
	public MCObjectType getType()
	{
		return null;
	}

	@Override
	public Vec3d getPosition()
	{
		return new Vec3d(0D, 0D, 0D);
	}

	@Override
	public Vec3d getDirection()
	{
		return new Vec3d(0D, 0D, 0D);
	}

}
