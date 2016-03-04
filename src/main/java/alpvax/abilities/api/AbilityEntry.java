package alpvax.abilities.api;

import java.util.List;

import alpvax.abilities.api.ability.IAbilityState;
import net.minecraft.entity.Entity;

public abstract class AbilityEntry
{
	public final IAbilityEffect effect;
	private List<Entity> targets = null;
	
	public AbilityEntry(IAbilityEffect effect)
	{
		this.effect = effect;
	}

	public List<Entity> getAffectedTargets()
	{
		return targets;
	}

	public List<Entity> getTargets(IAbilityState state)
	{
		List<Entity> newTargets = getValidTargets(state);
		if(targets == null)
		{
			targets = newTargets;
		}
		else
		{
			for(Entity e : newTargets)
			{
				if(!targets.contains(e))
				{
					targets.add(e);
				}
			}
		}
		return targets;
	}
	
	protected abstract List<Entity> getValidTargets(IAbilityState state);
	
	public abstract int getMaxDuration(IAbilityState state);
	/**
	 * Called every game tick to determine if the effect's {@link #tick} method should be called.<br>
	 * @param state the state of the ability.
	 * @return true if the tick method should be called
	 */
	public abstract boolean shouldTick(IAbilityState state);

	public boolean shouldReset(IAbilityState state)
	{
		return state.isActive() && state.ticksActive() >= getMaxDuration(state);
	}
}
