package alpvax.skillsandabilities.util;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * A physical object in the world (i.e. an {@linkplain Entity} or {@linkplain TileEntity}) which implements
 * {@linkplain ICapabilityProvider}.<br>
 * Not an {@linkplain ItemStack}, as the representation of that in world is an {@linkplain EntityItem} (Or some custom
 * Entity implementation).
 */
public interface ICapProviderObject
{
	public enum EntityType
	{
		ENTITY(Entity.class), TILEENTITY(TileEntity.class);

		private Class<? extends ICapabilityProvider> typeClass;

		private EntityType(Class<? extends ICapabilityProvider> type)
		{
			typeClass = type;
		}

		public static EntityType get(ICapabilityProvider object)
		{
			return object instanceof Entity ? EntityType.ENTITY : object instanceof TileEntity ? EntityType.TILEENTITY : null;
		}
	}

	@SuppressWarnings("unchecked")
	public default <T extends ICapabilityProvider> T getAttachedObject()
	{
		return (T)getType().typeClass.cast(capabilityProvider());
	}

	public ICapabilityProvider capabilityProvider();

	public default EntityType getType()
	{
		return EntityType.get(capabilityProvider());
	}

	public default Vec3d getPosition()
	{
		switch(getType()){
		case ENTITY:
			return this.<Entity>getAttachedObject().getPositionVector();
		case TILEENTITY:
			BlockPos pos = this.<TileEntity>getAttachedObject().getPos();
			return new Vec3d((pos.getX()) + 0.5D, (pos.getY()) + 0.5D, (pos.getZ()) + 0.5D);
		default:
			return Vec3d.ZERO;
		}
	}

	public default Vec3d getDirection()
	{
		switch(getType()){
		case ENTITY:
			return this.<Entity>getAttachedObject().getLookVec();
		case TILEENTITY:
			TileEntity t = getAttachedObject();
			IBlockState state = t.getWorld().getBlockState(t.getPos());
			if(state.getPropertyNames().contains(BlockDirectional.FACING))
			{
				return new Vec3d(state.getValue(BlockDirectional.FACING).getDirectionVec());
			}
			return Vec3d.ZERO;
		default:
			return Vec3d.ZERO;
		}
	}
}
