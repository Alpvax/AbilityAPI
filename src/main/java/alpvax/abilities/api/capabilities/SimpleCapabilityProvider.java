package alpvax.abilities.api.capabilities;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class SimpleCapabilityProvider<T> implements ICapabilityProvider
{
	private T handler;
	private Capability<T> cap;

	public SimpleCapabilityProvider(T capabilityHandler, Capability<T> capability)
	{
		handler = capabilityHandler != null ? capabilityHandler : capability.getDefaultInstance();
		cap = capability;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == cap;
	}

	@SuppressWarnings({"unchecked", "hiding"})
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return (T)(capability == cap ? getHandler() : null);
	}

	protected T getHandler()
	{
		return handler;
	}
}
