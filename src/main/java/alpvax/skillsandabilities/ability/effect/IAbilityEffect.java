package alpvax.skillsandabilities.ability.effect;

import alpvax.skillsandabilities.character.ICharacter;
import alpvax.skillsandabilities.target.IAbilityTarget;

public interface IAbilityEffect
{
	/**
	 * Called to start the ability.<br>
	 * Should be used to add attribute modifiers etc.
	 * @param character The object triggering the ability.
	 * @param target The target affected by the ability.
	 */
	public void trigger(ICharacter character, IAbilityTarget target);

	/**
	 * Called to stop the ability. Must revert any changes from {@link #trigger}<br>
	 * Should be used to remove attribute modifiers etc.<br>
	 * Also called if the abilities are removed from the provider, so the affected targets should be left in a state as
	 * though they didn't have abilities.
	 * @param character The object resetting the ability.
	 * @param target The target affected by the ability.
	 */
	public void reset(ICharacter character, IAbilityTarget target);

	/**
	 * Called every time the effect ticks (as determined by {@link EffectInstance#shouldTick}).<br>
	 * Should be used for e.g. abilities that have an effect that does something every x seconds
	 * @param character The object that has the ability active.
	 * @param target The target affected by the ability.
	 */
	public void tick(ICharacter character, IAbilityTarget target);

	/**
	 * The default number of game ticks between the {@linkplain #tick} method being called.<br>
	 * Called by the default implementation of {@link EffectTemplate#shouldTick}.
	 * @return the number of ticks before the effect ticks again.
	 */
	//TODO:public int tickrate();
}