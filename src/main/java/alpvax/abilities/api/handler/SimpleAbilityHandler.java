package alpvax.abilities.api.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.provider.IAbilityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class SimpleAbilityHandler implements IAbilityHandler
{
	private final ICapabilityProvider handled;
	private UUID id = UUID.randomUUID();
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

	@Override
	public List<IAbilityProvider> getProviders()
	{
		return new ArrayList<>(providers);
	}

}
