package alpvax.abilities.api.ability;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.provider.IAbilityProvider;

public class Ability
{
	public static interface IAbilityFactory
	{
		public Ability newAbility();
	};

	private UUID providerID;

	protected Ability(IAbilityProvider provider)
	{
		providerID = provider.getID();
		CapabilityAbilityHandler.Registry.INSTANCE.registerAbility(this);
	}

	public UUID getID()
	{
		return UUID.randomUUID();//TODO
	}

	public IAbilityProvider getProvider()
	{
		return CapabilityAbilityHandler.getProviderByID(providerID);
	}

	public void tick()
	{
		//TODO: Implement
	}

	public List<IAbilityAffected> getTargets()
	{
		//TODO: Implement
		return new ArrayList<>();
	}

	public void trigger()
	{
		//TODO: Implement
	}

	public void reset()
	{
		//TODO: Implement
	}

	public boolean isActive()
	{
		//TODO: Implement
		return false;
	}
}