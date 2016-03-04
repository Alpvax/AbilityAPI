package alpvax.abilities.api.ability.state;

import alpvax.abilities.api.IAbilityHandler;
import alpvax.abilities.api.IAbilityProvider;
import alpvax.abilities.api.ability.IAbilityState;

public class AbilityState implements IAbilityState
{
	private final IAbilityProvider provider;
	/**
	 * The number of ticks since the power became active.
	 */
	private int ticksActive;
	private int maxCooldown;
	private int cooldown;
	
	public AbilityState(IAbilityProvider provider)
	{
		this.provider = provider;
	}

	@Override
	public int remainingCooldown()
	{
		return cooldown;
	}

	@Override
	public boolean shouldTrigger()
	{
		return false;
	}

	@Override
	public boolean isActive()
	{
		return ticksActive > 0;
	}

	public void tick()
	{
		if(isActive())
		{
			ticksActive++;
		}
		if(cooldown > 0)
		{
			cooldown--;
		}
	}
	public void trigger()
	{
		if(getHandler() != null)
		{
			cooldown += maxCooldown;
		}
	}

	@Override
	public IAbilityHandler getHandler()
	{
		return provider.getHandler();
	}

	@Override
	public int ticksActive()
	{
		return ticksActive;
	}
}
