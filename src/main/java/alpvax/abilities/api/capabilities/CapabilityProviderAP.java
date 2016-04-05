package alpvax.abilities.api.capabilities;

import alpvax.abilities.api.provider.IAbilityProvider;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityProviderAP implements ICapabilitySerializable<NBTTagList>
{
	private IAbilityProvider providerHandler;

	public CapabilityProviderAP(IAbilityProvider handler)
	{
		providerHandler = handler;
	}

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
}