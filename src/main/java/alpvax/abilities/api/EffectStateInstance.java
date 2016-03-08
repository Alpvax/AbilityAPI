package alpvax.abilities.api;

import java.util.List;

import alpvax.abilities.affected.IAbilityAffected;
import alpvax.abilities.api.ability.state.AbilityState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class EffectStateInstance implements INBTSerializable<NBTTagCompound>
{
	public final IAbilityEffect effect;
	private List<IAbilityAffected> targets = null;
	/**
	 * The number of ticks since the power became active.
	 */
	private int ticksActive;
	
	public EffectStateInstance(IAbilityEffect effect)
	{
		this.effect = effect;
	}

	public List<IAbilityAffected> getAffectedTargets()
	{
		return targets;
	}

	public List<IAbilityAffected> getTargets(AbilityState state)
	{
		List<IAbilityAffected> newTargets = getValidTargets(state);
		if(targets == null)
		{
			targets = newTargets;
		}
		else
		{
			for(IAbilityAffected e : newTargets)
			{
				if(!targets.contains(e))
				{
					targets.add(e);
				}
			}
		}
		return targets;
	}
	
	protected abstract List<IAbilityAffected> getValidTargets(AbilityState state);
	
	public abstract int getMaxDuration(AbilityState state);
	/**
	 * Called every game tick to determine if the effect's {@link #tick} method should be called.<br>
	 * @param state the state of the ability.
	 * @return true if the tick method should be called
	 */
	public abstract boolean shouldTick(AbilityState state);
	
	public boolean isActive()
	{
		return ticksActive > 0;
	}

	public boolean shouldReset(AbilityState state)
	{
		return isActive() && ticksActive >= getMaxDuration(state);
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		// TODO Auto-generated method stub
		
	}

	public void trigger(AbilityState state)
	{
		for(IAbilityAffected affected : getTargets(state))
		{
			effect.trigger(state.getHandler(), affected);
		}
	}

	public void reset(AbilityState state)
	{
		for(IAbilityAffected affected : getTargets(state))
		{
			effect.trigger(state.getHandler(), affected);
		}
	}
}
