package alpvax.abilities.api.handler;

import java.util.List;

import alpvax.abilities.api.provider.IAbilityProvider;
import alpvax.abilities.api.util.IMCObject;
import alpvax.abilities.api.util.IUUIDKeyed;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * All instances must call {@link CapabilityAbilityHandler#register(this)} in order for them to work properly
 * @author Alpvax
 */
public interface IAbilityHandler extends IUUIDKeyed, IMCObject
{
	public ICapabilityProvider getHandled();

	public void updateProviderList();

	public List<IAbilityProvider> getAttachedProviders();

	public List<IAbilityProvider> getAllProviders();

	public void grantPowers(IAbilityProvider provider);
}
