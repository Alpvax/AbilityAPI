package alpvax.abilities.api;

import net.minecraft.tileentity.TileEntity;

public class AbilityHandlerTileEntity implements IAbilityHandler
{
	private final TileEntity tile;
	
	public AbilityHandlerTileEntity(TileEntity e)
	{
		tile = e;
	}
}
