package alpvax.abilities.api.affected;

import java.util.Map;

import com.google.common.base.Predicate;

import alpvax.abilities.api.ability.Ability;
import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.effect.IAbilityEffect;
import alpvax.abilities.api.provider.IAbilityProvider;

public interface IAbilityAffected
{
	public class AffectKey
	{
		public final IAbilityProvider provider;
		private final String abilityKey;

		public AffectKey(IAbilityProvider p, String key)
		{
			provider = p;
			abilityKey = key;
		}

		public Ability getAbility()
		{
			for(Ability a : provider.getAbilities())
			{
				if(a.getKey().equals(abilityKey))
				{
					return a;
				}
			}
			return null;
		}
	}

	public Object getAffected();

	public void add(EffectInstance effect);

	public void remove(EffectInstance effect);

	public Map<AffectKey, EffectInstance> getEffects();

	public boolean hasEffect(Predicate<IAbilityEffect> filter);

	public void tick();
}
