package alpvax.abilities.api;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class AbilityHandlerEntity extends AbilityHandlerBase
{
	private final Entity entity;

	public AbilityHandlerEntity(Entity e)
	{
		entity = e;
	}

	@Override
	public Vec3 getPositionVector()
	{
		return entity.getPositionVector();
	}

	@Override
	public BlockPos getPosition()
	{
		return entity.getPosition();
	}
}
