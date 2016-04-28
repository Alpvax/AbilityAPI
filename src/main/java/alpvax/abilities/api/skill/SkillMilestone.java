package alpvax.abilities.api.skill;

import com.google.common.base.Throwables;

import alpvax.abilities.core.AbilitiesAPIConstants;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class SkillMilestone implements INBTSerializable<NBTTagCompound>
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

	public Skill getSkill()
	{
		return skill;
	}

	/**
	 * Return null to use default implementation (save true boolean if achieved)
	 */
	@Override
	public NBTTagCompound serializeNBT()
	{
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		//Default nothing to serialize
	}

	public static final SkillMilestone loadFromNBT(Skill skill, String milestoneKey, NBTBase base)
	{
		if(base instanceof NBTTagCompound)
		{
			SkillMilestone sm = null;
			NBTTagCompound nbt = (NBTTagCompound)base;
			if(nbt.hasKey(AbilitiesAPIConstants.KEY_INSTANCE_CLASS))
			{
				String cname = nbt.getString(AbilitiesAPIConstants.KEY_INSTANCE_CLASS);
				try
				{
					Class<?> clazz = Class.forName(cname);
					sm = SkillMilestone.class.cast(clazz.getConstructor(Skill.class, String.class).newInstance(skill, milestoneKey));
				}
				catch(Exception e)
				{
					Throwables.propagate(e);
				}
			}
			return sm;
		}
		return ((NBTTagByte)base).getByte() != 0 ? skill.getMilestone(milestoneKey) : null;
	}
}
