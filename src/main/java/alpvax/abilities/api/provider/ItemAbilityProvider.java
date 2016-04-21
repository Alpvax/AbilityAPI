package alpvax.abilities.api.provider;

import alpvax.abilities.api.capabilities.SerializableCapabilityProvider.CapabilityProviderAP;
import alpvax.abilities.api.provider.IAbilityProvider.AbilityProviderFactory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants.NBT;

public abstract class ItemAbilityProvider extends Item
{
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
	{
		return new CapabilityProviderAP(getFactory().newProvider()).loadHandler(nbt.getTagList("Parent", NBT.TAG_LIST));
	}

	protected abstract AbilityProviderFactory getFactory();
}
