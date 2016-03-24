package alpvax.abilities.api.affected;

import com.google.common.base.Predicate;

import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.effect.IAbilityEffect;

public interface IAbilityAffected
{
	public Object getAffected();

	public void add(EffectInstance effect);

	public void remove(EffectInstance effect);

	public boolean hasEffect(Predicate<IAbilityEffect> filter);

	public void tick();
}
