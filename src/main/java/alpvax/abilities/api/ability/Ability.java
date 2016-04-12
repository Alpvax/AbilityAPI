package alpvax.abilities.api.ability;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;

public class Ability
{
	public static interface IAbilityFactory extends Callable<Ability>
	{
	};

	public Ability()
	{
		CapabilityAbilityHandler.Registry.INSTANCE.registerAbility(this);
	}

	public UUID getID()
	{
		return UUID.randomUUID();//TODO
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