package alpvax.racemod.race;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IEntityRace extends INBTSerializable<NBTTagCompound>
{
	public IEntityRace setEntity(Entity entity);

	public Entity getEntity();

	public void setRace(String raceID);

	public EntityRace getRace();
}
