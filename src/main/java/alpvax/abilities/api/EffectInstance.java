package alpvax.abilities.api;

import java.util.List;

import alpvax.abilities.api.ability.state.AbilityState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class EffectInstance implements INBTSerializable<NBTTagCompound>
{
	public final IAbilityEffect effect;
	private List<Entity> targets = null;
	
	public EffectInstance(IAbilityEffect effect)
	{
		this.effect = effect;
	}

	public List<Entity> getAffectedTargets()
	{
		return targets;
	}

	public List<Entity> getTargets(AbilityState state)
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
	
	protected abstract List<Entity> getValidTargets(AbilityState state);
	
	public abstract int getMaxDuration(AbilityState state);
	/**
	 * Called every game tick to determine if the effect's {@link #tick} method should be called.<br>
	 * @param state the state of the ability.
	 * @return true if the tick method should be called
	 */
	public abstract boolean shouldTick(AbilityState state);

	public boolean shouldReset(AbilityState state)
	{
		return state.isActive() && state.ticksActive() >= getMaxDuration(state);
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
}
