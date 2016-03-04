package alpvax.abilities.api;

import java.util.List;

import net.minecraft.entity.Entity;

public interface IAbilityEffect
{
	/**
	 * Called to start the power.<br>
	 * Should be used to add attribute modifiers etc.
	 * @param abilityhandler The object triggering the power.
	 * @param targets The entities affected by the power
	 */
	public void trigger(IAbilityHandler abilityhandler, List<Entity> targets);
	/**
	 * Called to stop the power. Must revert any changes from {@link #trigger}<br>
	 * Should be used to remove attribute modifiers etc.<br>
	 * Also called if the powers are removed from the provider,
	 * so the affected targets should be left in a state as though they didn't have powers.
	 * @param abilityhandler The object resetting the power.
	 * @param targets The entities affected by the power
	 */
	public void reset(IAbilityHandler abilityhandler, List<Entity> targets);
	/**
	 * Called every time the effect ticks (as determined by {@link #shouldTick}).<br>
	 * Should be used for e.g. powers that have an effect that does something every x seconds
	 * @param abilityhandler The object that has the power active.
	 * @param targets The entities affected by the power
	 */
	public void tick(IAbilityHandler abilityhandler, List<Entity> targets);
}