package alpvax.abilities.affected;

import alpvax.abilities.api.provider.IAbilityProvider;

public interface IAbilityAffected
{
	public static enum AbilityAffectedType
	{
		ENTITY,
		TILE
	}
	public AbilityAffectedType getType();
	
	public void addProvider(IAbilityProvider provider);
	public void removeProvider(IAbilityProvider provider);
}
