package alpvax.skillsandabilities.target;

import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class SimpleAbilityTarget implements IAbilityTarget
{
	private final ICapabilityProvider capabilityProvider;

	public SimpleAbilityTarget(ICapabilityProvider attached)
	{
		capabilityProvider = attached;
	}

	@Override
	public ICapabilityProvider capabilityProvider()
	{
		return capabilityProvider;
	}
}
