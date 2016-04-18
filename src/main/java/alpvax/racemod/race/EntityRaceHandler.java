package alpvax.racemod.race;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagString;

public class EntityRaceHandler implements IEntityRace
{
	private Entity entity = null;
	private String raceID = null;

	@Override
	public NBTTagString serializeNBT()
	{
		return new NBTTagString(raceID);
	}

	@Override
	public void deserializeNBT(NBTTagString nbt)
	{
		raceID = nbt.getString();
	}

	@Override
	public IEntityRace setEntity(Entity entity)
	{
		this.entity = entity;
		return this;
	}

	@Override
	public EntityRace getRace()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
