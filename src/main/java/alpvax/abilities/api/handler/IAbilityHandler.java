package alpvax.abilities.api.handler;

import java.util.List;

import alpvax.abilities.api.capabilities.IKeyedCapability;
import alpvax.abilities.api.provider.IAbilityProvider;
import alpvax.abilities.api.util.IMCObject;

/**
 * All instances must call {@link CapabilityAbilityHandler#register(this)} in order for them to work properly
 * @author Alpvax
 */
public interface IAbilityHandler extends IKeyedCapability, IMCObject
{
	public Object getHandled();

	public void updateProviderList();

	public List<IAbilityProvider> getProviders();

	public void grantPowers(IAbilityProvider provider);
}
