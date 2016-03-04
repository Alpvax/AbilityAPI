package alpvax.abilities.api.ability;

import java.util.ArrayList;
import java.util.List;

import alpvax.abilities.api.EffectInstance;
import alpvax.abilities.api.IAbilityHandler;
import alpvax.abilities.api.ability.state.AbilityState;

public class Ability
{	
	private List<EffectInstance> effects = new ArrayList<>();
	

	public void trigger(AbilityState state)
	{
		IAbilityHandler handler = state.getHandler();
		for(EffectInstance e : effects)
		{
			e.effect.trigger(handler, e.getTargets(state));
		}
	}
	public void reset(AbilityState state)
	{
		IAbilityHandler handler = state.getHandler();
		for(EffectInstance e : effects)
		{
			e.effect.reset(handler, e.getAffectedTargets());
		}
	}
	public void tick(AbilityState state)
	{
		IAbilityHandler handler = state.getHandler();
		for(EffectInstance e : effects)
		{
			if(e.shouldReset(state))
			{
				e.effect.reset(handler, e.getAffectedTargets());
			}
			if(e.shouldTick(state))
			{
				e.effect.tick(handler, e.getAffectedTargets());
			}
		}
	}
	
	public Ability addEffect(EffectInstance effect)
	{
		effects.add(effect);
		return this;
	}
}
