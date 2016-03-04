package alpvax.abilities.api;

import java.util.List;

import com.google.common.base.Predicate;

import alpvax.abilities.api.ability.state.AbilityState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class AbilityHandlerEntity implements IAbilityHandler
{
	private final Entity entity;
	
	public AbilityHandlerEntity(Entity e)
	{
		entity = e;
	}

	@Override
	public List<AbilityState> getAbilities(Predicate<AbilityState> filter)
	{
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public double getDistanceSq(double x, double y, double z)
	{
		return getPositionVector().squareDistanceTo(new Vec3(x, y, z));
	}
}
