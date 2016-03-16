package alpvax.abilities.sample.item;

import java.util.List;

import com.google.common.base.Predicate;

import alpvax.abilities.api.IAbilityHandler;
import alpvax.abilities.api.IAbilityProvider;
import alpvax.abilities.api.ability.state.AbilityState;
import alpvax.abilities.core.CapabilityAbilityHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public abstract class ItemAbilityProvider extends Item implements IAbilityProvider
{
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
	{
		return new ICapabilitySerializable<NBTTagCompound>(){
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
			public NBTTagCompound serializeNBT()
			{
				return providerHandler.serializeNBT();
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt)
			{
				providerHandler.deserializeNBT(nbt);
			}
		};
	}

	@Override
	public List<AbilityState> getAbilities(Predicate<AbilityState> filter)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAbilityHandler getHandler()
	{
		// TODO Auto-generated method stub
		return null;
	}

	protected
}
