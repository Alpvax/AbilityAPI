package alpvax.racemod.race;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.INBTSerializable;

public interface IEntityRace extends INBTSerializable<NBTTagString>
{
	public IEntityRace setEntity(Entity entity);

	public EntityRace getRace();

}
