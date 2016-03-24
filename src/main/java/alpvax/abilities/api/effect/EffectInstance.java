package alpvax.abilities.api.effect;

import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.provider.IAbilityProvider;

public class EffectInstance
{
	private final EffectTemplate template;
	private final IAbilityProvider provider;
	private final IAbilityAffected affected;

	protected int ticksActive = 0;
	/** The number of game ticks since the effect was triggered */
	public int ticksSinceLast = 0;

	public EffectInstance(IAbilityAffected affected, IAbilityProvider provider, EffectTemplate template)
	{
		this.affected = affected;
		this.provider = provider;
		this.template = template;
	}

	public boolean shouldTick()
	{
		return isActive() && template.shouldTick(this, affected);
	}

	public boolean isActive()
	{
		return ticksActive > 0;
	}

	public void onAttach()
	{
		if(template.shouldTrigger(this, affected))
		{
			template.effect.trigger(provider, affected);
		}
	}

	public void onDetach()
	{
		template.effect.reset(provider, affected);
	}
}
