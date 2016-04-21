package alpvax.abilities.api.capabilities;

import java.util.UUID;

/**
 * All instances must call {@link CapabilityAbilityHandler#register(this)} in order for them to work properly
 * @author Alpvax
 */
public interface IKeyedCapability
{
	/**
	 * @return the UUID to use when registering with {@link CapabilityAbilityHandler#register}
	 */
	public UUID getKey();
}
