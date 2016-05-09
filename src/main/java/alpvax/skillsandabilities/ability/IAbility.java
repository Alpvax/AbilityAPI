package alpvax.skillsandabilities.ability;

import java.util.UUID;

import alpvax.skillsandabilities.character.ICharacterModifier;

public interface IAbility
{

	public UUID getID();

	public static abstract class AbilityFactory
	{
		public abstract IAbility newAbility(ICharacterModifier source);
	}

	public boolean acceptsInput();

	public void onInput();

	public AbilityState getState();

	public Iterable<AbilityState> getStates();

	public AbilityState checkState(int ticksSinceStateChange);

	public default AbilityState getDefaultState()
	{
		return getStates().iterator().next();
	}

	public void tick();
}
