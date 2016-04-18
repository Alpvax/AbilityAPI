package alpvax.racemod.role;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagString;

public class EntityRoleHandler implements IEntityRole
{
	private Entity entity = null;
	private String roleID = null;

	@Override
	public NBTTagString serializeNBT()
	{
		return new NBTTagString(roleID);
	}

	@Override
	public void deserializeNBT(NBTTagString nbt)
	{
		roleID = nbt.getString();
	}

	@Override
	public IEntityRole setEntity(Entity entity)
	{
		this.entity = entity;
		return this;
	}

	@Override
	public EntityRole getRole()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
