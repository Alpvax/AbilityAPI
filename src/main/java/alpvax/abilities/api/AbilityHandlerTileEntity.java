package alpvax.abilities.api;

import java.util.List;

import com.google.common.base.Predicate;

import alpvax.abilities.api.ability.state.AbilityState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class AbilityHandlerTileEntity implements IAbilityHandler
{
	private final TileEntity tile;

	public AbilityHandlerTileEntity(TileEntity e)
	{
		tile = e;
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
		BlockPos pos = getPosition();
		return new Vec3((pos.getX()) + 0.5D, (pos.getY()) + 0.5D, (pos.getZ()) + 0.5D);
	}

	@Override
	public BlockPos getPosition()
	{
		return tile.getPos();
	}

	@Override
	public double getDistanceSq(double x, double y, double z)
	{
		return getPositionVector().squareDistanceTo(new Vec3(x, y, z));
	}
}
