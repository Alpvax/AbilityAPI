package alpvax.abilities.api.provider;

import alpvax.outdated.abilities.capabilities.CapabilityAbilityHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class AbilityProviderItem implements ICapabilitySerializable<NBTTagCompound>
{
    @CapabilityInject(IAbilityProvider.class)
    private static Capability<IAbilityProvider> ABILITY_PROVIDER_CAPABILITY = null;
    
    private IAbilityProvider abilityProvider;
    
    public AbilityProviderItem(ItemStack stack)
    {
    	
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == ABILITY_PROVIDER_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == CapabilityAbilityHandler.ABILITY_PROVIDER_CAPABILITY ? (T);
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		
	}
}
