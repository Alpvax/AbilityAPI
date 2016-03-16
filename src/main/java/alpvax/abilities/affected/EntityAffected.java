package alpvax.abilities.affected;

import net.minecraft.entity.Entity;

public class EntityAffected extends AbilityAffectedBase
{
	public final Entity affectedEntity;

	public EntityAffected(Entity e)
	{
		super(e);
		affectedEntity = e;
	}
}
