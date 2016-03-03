package alpvax.abilities.api;

import net.minecraft.entity.Entity;

public class AbilityHandlerEntity implements IAbilityHandler
{
	private final Entity entity;
	
	public AbilityHandlerEntity(Entity e)
	{
		entity = e;
	}
}
