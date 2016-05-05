package alpvax.skillsandabilities.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class SerializableCapabilityProvider<T, S extends NBTBase> extends SimpleCapabilityProvider<T> implements INBTSerializable<S>
{
	public SerializableCapabilityProvider(T capabilityHandler, Capability<T> capability)
	{
		super(capabilityHandler, capability);
	}

	@SuppressWarnings("unchecked")
	@Override
	public S serializeNBT()
	{
		return ((INBTSerializable<S>)getHandler()).serializeNBT();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deserializeNBT(S nbt)
	{
		((INBTSerializable<S>)getHandler()).deserializeNBT(nbt);
	}

	public ICapabilityProvider loadHandler(S nbt)
	{
		deserializeNBT(nbt);
		return this;
	}
}