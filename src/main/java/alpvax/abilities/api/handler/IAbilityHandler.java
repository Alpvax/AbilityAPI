package alpvax.abilities.api.handler;

import java.util.List;

import alpvax.abilities.api.capabilities.IKeyedCapability;
import alpvax.abilities.api.provider.IAbilityProvider;

/**
 * All instances must call {@link CapabilityAbilityHandler#register(this)} in order for them to work properly
 * @author Alpvax
 */
public interface IAbilityHandler extends IKeyedCapability
{
	public Object getHandled();

	public void updateProviderList();

	public List<IAbilityProvider> getProviders();
}
