package alpvax.abilities.api;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class AbilityHandlerTileEntity extends AbilityHandlerBase
{
	private final TileEntity tile;

	public AbilityHandlerTileEntity(TileEntity e)
	{
		tile = e;
	}

	@Override
	public BlockPos getPosition()
	{
		return tile.getPos();
	}
}
