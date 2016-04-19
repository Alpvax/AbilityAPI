package alpvax.racemod.race;

import alpvax.racemod.core.RaceModConstants;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

public class EntityRaceHandler implements IEntityRace
{
	private Entity entity = null;
	private EntityRace race = null;

	public EntityRaceHandler(Entity e)
	{
		setEntity(e);
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString(RaceModConstants.TAG_RACE, race.getID());
		nbt.setTag(RaceModConstants.TAG_ABILITIES, race.serializeNBT());
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		setRace(nbt.getString(RaceModConstants.TAG_RACE));
		race.deserializeNBT(nbt.getTagList(RaceModConstants.TAG_ABILITIES, NBT.TAG_COMPOUND));
	}

	@Override
	public IEntityRace setEntity(Entity entity)
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
	public void setRace(String raceID)
	{
		race = EntityRace.newInstance(raceID, entity);
	}

	@Override
	public EntityRace getRace()
	{
		return race;
	}
}
