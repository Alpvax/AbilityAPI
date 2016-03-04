package alpvax.abilities.api.ability;

import java.util.ArrayList;
import java.util.List;

import alpvax.abilities.api.AbilityEntry;
import alpvax.abilities.api.IAbilityHandler;

public class Ability
{	
	private List<AbilityEntry> effects = new ArrayList<>();
	

	public void trigger(IAbilityState state)
	{
		IAbilityHandler handler = state.getHandler();
		for(AbilityEntry e : effects)
		{
			e.effect.trigger(handler, e.getTargets(state));
		}
	}
	public void reset(IAbilityState state)
	{
		IAbilityHandler handler = state.getHandler();
		for(AbilityEntry e : effects)
		{
			e.effect.reset(handler, e.getAffectedTargets());
		}
	}
	public void tick(IAbilityState state)
	{
		IAbilityHandler handler = state.getHandler();
		for(AbilityEntry e : effects)
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
	
	public Ability addEffect(AbilityEntry effect)
	{
		effects.add(effect);
		return this;
	}
}
