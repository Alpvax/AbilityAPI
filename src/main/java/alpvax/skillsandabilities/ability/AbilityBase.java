package alpvax.skillsandabilities.ability;

import java.util.UUID;

import alpvax.skillsandabilities.character.ICharacterModifier;

public abstract class AbilityBase implements IAbility
{
	private int ticksSinceStateChange = 0;
	private AbilityState currentState = null;
	private final ICharacterModifier container;

	public AbilityBase(ICharacterModifier modifier)
	{
		container = modifier;
		setState(getDefaultState());
	}

	@Override
	public UUID getID()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean acceptsInput()
	{
		return false;
	}

	@Override
	public void onInput()
	{
	}

	@Override
	public AbilityState getState()
	{
		return currentState;
	}

	@Override
	public AbilityState getDefaultState()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<AbilityState> getStates()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tick()
	{
		AbilityState newState = checkState(ticksSinceStateChange++);
		if(getState() != newState)
		{
			setState(newState);
		}
	}

	public void setState(AbilityState newState)
	{
		if(currentState != null)
		{
			currentState.reset(container);
		}
		newState.trigger(container);
		currentState = newState;
	}

}
