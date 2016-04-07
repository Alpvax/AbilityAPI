package alpvax.abilities.api.provider;

import com.google.common.base.Throwables;

import alpvax.abilities.api.capabilities.SerializableCapabilityProvider.CapabilityProviderAP;
import alpvax.abilities.api.provider.IAbilityProvider.IAbilityProviderFactory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public abstract class ItemAbilityProvider extends Item implements IAbilityProviderFactory
{
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
	{
		try
		{
			return new CapabilityProviderAP(call());
		}
		catch(Exception e)
		{
			Throwables.propagate(e);
			return super.initCapabilities(stack, nbt);
		}
	}
}
