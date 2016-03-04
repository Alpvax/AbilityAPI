package alpvax.abilities.api.ability;

import alpvax.abilities.api.IAbilityHandler;

public interface IAbilityState
{
	public int remainingCooldown();
	public int ticksActive();
	public boolean shouldTrigger();
	public boolean isActive();
	public IAbilityHandler getHandler();
}
