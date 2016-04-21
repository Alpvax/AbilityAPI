package alpvax.abilities.api.affected;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class TileEntityAffected extends SimpleAbilityAffected
{

	public TileEntityAffected(TileEntity affected)
	{
		super(affected);
	}

	public TileEntity getTileEntity()
	{
		return (TileEntity)getAffected();
	}

	@Override
	public MCObjectType getType()
	{
		return MCObjectType.TILEENTITY;
	}

	@Override
	public Vec3d getPosition()
	{
		BlockPos pos = getTileEntity().getPos();
		return new Vec3d((pos.getX()) + 0.5D, (pos.getY()) + 0.5D, (pos.getZ()) + 0.5D);
	}

	@Override
	public Vec3d getDirection()
	{
		TileEntity t = getTileEntity();
		IBlockState state = t.getWorld().getBlockState(t.getPos());
		if(state.getPropertyNames().contains(BlockDirectional.FACING))
		{
			return new Vec3d(state.getValue(BlockDirectional.FACING).getDirectionVec());
		}
		return super.getDirection();
	}
}
