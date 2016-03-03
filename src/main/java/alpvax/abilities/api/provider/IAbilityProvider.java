package alpvax.abilities.api.provider;

import net.minecraft.nbt.NBTTagCompound;

public interface IAbilityProvider
{
	public void readFromNBT(NBTTagCompound nbt);
	public void writeToNBT(NBTTagCompound nbt);
}
