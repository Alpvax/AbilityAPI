package alpvax.abilities.api.affected;

import java.util.Map;

import com.google.common.base.Predicate;

import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.effect.IAbilityEffect;
import alpvax.abilities.util.EffectMap;

public class AbilityAffectedBase implements IAbilityAffected
{
	private final Object affected;
	private EffectMap effects = new EffectMap();

	public AbilityAffectedBase(Object affected)
	{
		this.affected = affected;
	}

	@Override
	public Object getAffected()
	{
		return affected;
	}

	@Override
	public void add(EffectInstance effect)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(EffectInstance effect)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasEffect(Predicate<IAbilityEffect> filter)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void tick()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Map<AffectKey, EffectInstance> getEffects()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
