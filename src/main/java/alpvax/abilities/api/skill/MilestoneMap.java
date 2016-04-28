package alpvax.abilities.api.skill;

import alpvax.abilities.api.util.NBTMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class MilestoneMap extends NBTMap<SkillMilestone>
{
	private final Skill skill;

	public MilestoneMap(Skill skill)
	{
		super(SkillMilestone.class);
		this.skill = skill;
	}

	@Override
	protected Class<?>[] getCtorTypes(String nbtKey, NBTTagCompound nbt)
	{

		return new Class[]{Skill.class, String.class};
	}

	@Override
	protected Object[] getCtorArgs(String nbtKey, NBTTagCompound nbt)
	{
		return new Object[]{skill, nbtKey};
	}

	@Override
	protected SkillMilestone newBaseInstance(String key, NBTBase base)
	{
		return skill.getMilestone(key);
	}
}
