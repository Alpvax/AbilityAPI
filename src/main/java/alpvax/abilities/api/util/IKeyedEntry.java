package alpvax.abilities.api.util;

import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;

/**
 * All instances must call {@link CapabilityAbilityHandler#register(this)} in order for them to work properly
 * @author Alpvax
 */
public interface IKeyedEntry<T>
{
	/**
	 * @return the UUID to use when registering with {@link CapabilityAbilityHandler#register}
	 */
	public T getKey();

	public void setKey(T key);
}
