package alpvax.racemod.role;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IEntityRole extends INBTSerializable<NBTTagCompound>
{
	public IEntityRole setEntity(Entity entity);

	public Entity getEntity();

	public void setRole(String roleID);

	public EntityRole getRole();
}
