package alpvax.abilities.api.affected;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.effect.IAbilityEffect;
import net.minecraft.nbt.NBTTagList;

public class SimpleAbilityAffected implements IAbilityAffected
{
	private Object affected;
	private List<EffectInstance> effects = new ArrayList<>();

	public SimpleAbilityAffected(Object affected)
	{
		setAffected(affected);
	}

	public void setAffected(Object affected)
	{
		this.affected = affected;
	}

	@Override
	public Object getAffected()
	{
		return affected;
	}

	@Override
	public NBTTagList serializeNBT()
	{
		return (NBTTagList)CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY.getStorage().writeNBT(CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY, this, null);
	}

	@Override
	public void deserializeNBT(NBTTagList nbt)
	{
		CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY.getStorage().readNBT(CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY, this, null, nbt);
	}

	@Override
	public void add(EffectInstance effect)
	{
		effects.add(effect);
	}

	@Override
	public void remove(EffectInstance effect)
	{
		effects.remove(effect);
	}

	@Override
	public List<EffectInstance> getEffects()
	{
		return new ArrayList<>(effects);
	}

	@Override
	public boolean hasEffect(Predicate<IAbilityEffect> filter)
	{
		for(EffectInstance e : getEffects())
		{
			if(filter.apply(e.getEffect()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void tick()
	{
		for(EffectInstance e : getEffects())
		{
			e.tick();
		}
	}

	@Override
	public void removeAll(Predicate<EffectInstance> filter)
	{
		for(EffectInstance o : new ArrayList<EffectInstance>(effects))
		{
			if(filter.apply(o))
			{
				effects.remove(o);
			}
		}
	}

}
