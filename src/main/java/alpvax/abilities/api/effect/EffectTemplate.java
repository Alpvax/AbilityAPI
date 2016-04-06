package alpvax.abilities.api.effect;

import alpvax.abilities.api.affected.IAbilityAffected;

public abstract class EffectTemplate
{
	public final IAbilityEffect effect;

	public EffectTemplate(IAbilityEffect effect)
	{
		this.effect = effect;
	}

	public boolean shouldTick(EffectInstance ei, IAbilityAffected affected)
	{
		return ei.ticksActive % effect.tickrate() == 0;
	}

	public boolean shouldTrigger(EffectInstance ei, IAbilityAffected affected)
	{
		return !ei.isActive();
	}

	public boolean shouldReset(EffectInstance ei, IAbilityAffected affected)
	{
		return ei.isActive() && ei.ticksActive >= maxDuration(affected);
	}

	public int getCooldown(EffectInstance ei, IAbilityAffected affected)
	{
		return Math.max(maxCooldown(affected) - ei.ticksSinceLast, 0);
	}

	public abstract int maxCooldown(IAbilityAffected affected);

	public abstract int maxDuration(IAbilityAffected affected);

	public abstract boolean persistAcrossDeath();
}
