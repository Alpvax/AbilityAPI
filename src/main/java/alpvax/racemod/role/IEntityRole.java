package alpvax.racemod.role;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.INBTSerializable;

public interface IEntityRole extends INBTSerializable<NBTTagString>
{
	public IEntityRole setEntity(Entity entity);

	public EntityRole getRole();
}
