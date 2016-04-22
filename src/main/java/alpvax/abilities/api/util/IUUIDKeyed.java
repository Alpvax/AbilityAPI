package alpvax.abilities.api.util;

import java.util.UUID;

import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;

/**
 * All instances must call {@link CapabilityAbilityHandler#register(this)} in order for them to work properly
 * @author Alpvax
 */
public interface IUUIDKeyed
{
	/**
	 * @return the UUID to use when registering with {@link CapabilityAbilityHandler#register}
	 */
	public UUID getKey();

	public void setKey(UUID key);
}
