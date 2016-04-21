package alpvax.abilities.api.util;

import net.minecraft.util.math.Vec3d;

public interface IMCObject
{
	public enum MCObjectType
	{
		ENTITY, TILEENTITY;
	}

	public MCObjectType getType();

	public Vec3d getPosition();

	public Vec3d getDirection();
}
