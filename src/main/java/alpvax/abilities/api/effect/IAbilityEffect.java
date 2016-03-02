package alpvax.abilities.api.effect;

import java.util.List;

import alpvax.abilities.affected.IAbilityAffected;
import net.minecraft.entity.Entity;

public interface IAbilityEffect
{
	/**
	 * Called to start the power.<br>
	 * Should be used to add attribute modifiers etc.
	 * @param provider The object triggering the power.
	 * @param targets The entities affected by the power
	 */
	public void trigger(IAbilityAffected provider, List<Entity> targets);
	/**
	 * Called to stop the power. Must revert any changes from {@link #trigger}<br>
	 * Should be used to remove attribute modifiers etc.<br>
	 * Also called if the powers are removed from the provider,
	 * so the affected targets should be left in a state as though they didn't have powers.
	 * @param provider The object resetting the power.
	 * @param targets The entities affected by the power
	 */
	public void reset(IAbilityAffected provider, List<Entity> targets);
	/**
	 * Called every time the effect ticks (as determined by {@link #shouldTick}).<br>
	 * Should be used for e.g. powers that have an effect that does something every x seconds
	 * @param provider The object that has the power active.
	 * @param targets The entities affected by the power
	 */
	public void tick(IAbilityAffected provider, List<Entity> targets);
	/**
	 * Called every game tick to determine if the effect's {@link #tick} method should be called.<br>
	 * @param ticksSinceLast the number of ticks since the power last ticked.
	 * @return true if the tick method should be called
	 */
	public boolean shouldTick(int ticksSinceLast);
}
