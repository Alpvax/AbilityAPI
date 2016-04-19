package alpvax.racemod.role;

import alpvax.racemod.core.RaceModConstants;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

public class EntityRoleHandler implements IEntityRole
{
	private Entity entity = null;
	private EntityRole role = null;

	public EntityRoleHandler(Entity e)
	{
		setEntity(e);
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString(RaceModConstants.TAG_ROLE, role.getID());
		nbt.setTag(RaceModConstants.TAG_ABILITIES, role.serializeNBT());
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		setRole(nbt.getString(RaceModConstants.TAG_ROLE));
		role.deserializeNBT(nbt.getTagList(RaceModConstants.TAG_ABILITIES, NBT.TAG_COMPOUND));
	}

	@Override
	public IEntityRole setEntity(Entity entity)
	{
		this.entity = entity;
		return this;
	}

	@Override
	public Entity getEntity()
	{
		return entity;
	}

	@Override
	public void setRole(String roleID)
	{
		role = EntityRole.newInstance(roleID, entity);
	}

	@Override
	public EntityRole getRole()
	{
		return role;
	}
}
