package alpvax.skillsandabilities.util;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public interface ICapProviderObject
{
	public enum EntityType
	{
		ENTITY, TILEENTITY;

		public static EntityType get(ICapabilityProvider object)
		{
			return object instanceof Entity ? EntityType.ENTITY : object instanceof TileEntity ? EntityType.TILEENTITY : null;
		}
	}

	public ICapabilityProvider getAttachedObject();

	public default EntityType getType()
	{
		return EntityType.get(getAttachedObject());
	}

	public default Vec3d getPosition()
	{
		switch(getType()){
		case ENTITY:
			return ((Entity)getAttachedObject()).getPositionVector();
		case TILEENTITY:
			BlockPos pos = ((TileEntity)getAttachedObject()).getPos();
			return new Vec3d((pos.getX()) + 0.5D, (pos.getY()) + 0.5D, (pos.getZ()) + 0.5D);
		default:
			return Vec3d.ZERO;
		}
	}

	public default Vec3d getDirection()
	{
		switch(getType()){
		case ENTITY:
			return ((Entity)getAttachedObject()).getLookVec();
		case TILEENTITY:

			TileEntity t = (TileEntity)getAttachedObject();
			IBlockState state = t.getWorld().getBlockState(t.getPos());
			if(state.getPropertyNames().contains(BlockDirectional.FACING))
			{
				return new Vec3d(state.getValue(BlockDirectional.FACING).getDirectionVec());
			}
		default:
			return Vec3d.ZERO;
		}
	}
}
