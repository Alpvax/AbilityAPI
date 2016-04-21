package alpvax.abilities.api.affected;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class EntityAffected extends SimpleAbilityAffected
{

	public EntityAffected(Entity affected)
	{
		super(affected);
	}

	public Entity getEntity()
	{
		return (Entity)getAffected();
	}

	@Override
	public MCObjectType getType()
	{
		return MCObjectType.ENTITY;
	}

	@Override
	public Vec3d getPosition()
	{
		return getEntity().getPositionVector();
	}

	@Override
	public Vec3d getDirection()
	{
		return getEntity().getLookVec();
	}
}
