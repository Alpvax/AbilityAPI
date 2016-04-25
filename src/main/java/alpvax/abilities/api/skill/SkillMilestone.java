package alpvax.abilities.api.skill;

import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class SkillMilestone<T extends NBTBase> implements INBTSerializable<T>
{
	private final Skill skill;
	private final String id;

	public SkillMilestone(Skill skill, String key)
	{
		this.skill = skill;
		id = key;
	}

	/**
	 * Check whether the SkillInstance currently satisfies the required condition.
	 * @param skillInstance the SkillInstance to check
	 * @return true if the skillInstance passes the requirement
	 */
	public abstract boolean hasAchieved(SkillInstance skillInstance);

	/**
	 * Called every time the SkillInstance value is modified.
	 * @param achieved whether the milestone has previously been achieved.
	 * @return true if the milestone is achieved, false if not.
	 */
	public boolean onValueUpdate(ISkillHandler handler, boolean achieved)
	{
		SkillInstance i = handler.getInstance(skill);
		if(hasAchieved(i) && !achieved)
		{
			onAchieved(handler);
			return true;
		}
		else if(!hasAchieved(i) && achieved)
		{
			onUnachieved(handler);
			return false;
		}
		return achieved;
	}

	/**
	 * Called when the
	 * @param handler
	 */
	public abstract void onAchieved(ISkillHandler handler);

	public abstract void onUnachieved(ISkillHandler handler);

	public String getKey()
	{
		return id;
	}
}
