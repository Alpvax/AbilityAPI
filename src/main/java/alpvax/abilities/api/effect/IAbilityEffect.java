package alpvax.abilities.api.effect;

import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.provider.IAbilityProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAbilityEffect extends INBTSerializable<NBTTagCompound>
{
	/**
	 * Called to start the ability.<br>
	 * Should be used to add attribute modifiers etc.
	 * @param provider The object triggering the ability.
	 * @param target The entity affected by the ability.
	 */
	public void trigger(IAbilityProvider provider, IAbilityAffected target);

	/**
	 * Called to stop the ability. Must revert any changes from {@link #trigger}<br>
	 * Should be used to remove attribute modifiers etc.<br>
	 * Also called if the abilities are removed from the provider, so the affected targets should be left in a state as
	 * though they didn't have abilities.
	 * @param provider The object resetting the ability.
	 * @param target The entity affected by the ability.
	 */
	public void reset(IAbilityProvider provider, IAbilityAffected target);

	/**
	 * Called every time the effect ticks (as determined by {@link EffectInstance#shouldTick}).<br>
	 * Should be used for e.g. abilities that have an effect that does something every x seconds
	 * @param provider The object that has the ability active.
	 * @param target The entity affected by the ability.
	 */
	public void tick(IAbilityProvider provider, IAbilityAffected target);

	/**
	 * The default number of game ticks between the {@linkplain #tick} method being called.<br>
	 * Called by the default implementation of {@link EffectTemplate#shouldTick}.
	 * @return the number of ticks before the effect ticks again.
	 */
	public int tickrate();
}