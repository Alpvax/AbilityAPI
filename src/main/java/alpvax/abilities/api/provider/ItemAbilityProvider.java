package alpvax.abilities.api.provider;

import alpvax.abilities.api.provider.IAbilityProvider.IAbilityProviderFactory;
import alpvax.abilities.core.CapabilityAbilityHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public abstract class ItemAbilityProvider extends Item implements IAbilityProviderFactory
{
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
	{
		return new ICapabilitySerializable<NBTTagList>()
		{
			protected IAbilityProvider providerHandler;

			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing)
			{
				return capability == CapabilityAbilityHandler.ABILITY_PROVIDER_CAPABILITY;
			}

			@SuppressWarnings("unchecked")
			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing)
			{
				return (T)(capability == CapabilityAbilityHandler.ABILITY_PROVIDER_CAPABILITY ? providerHandler : null);
			}

			@Override
			public NBTTagList serializeNBT()
			{
				return providerHandler.serializeNBT();
			}

			@Override
			public void deserializeNBT(NBTTagList nbt)
			{
				providerHandler.deserializeNBT(nbt);
			}
		};
	}
}
