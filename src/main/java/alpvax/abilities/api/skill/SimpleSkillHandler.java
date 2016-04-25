package alpvax.abilities.api.skill;

import java.util.HashMap;
import java.util.Map;

import alpvax.abilities.core.AbilitiesAPIConstants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SimpleSkillHandler implements ISkillHandler
{
	private Map<String, SkillInstance> skills = new HashMap<>();

	@Override
	public boolean hasSkill(Skill skill)
	{
		return skills.containsKey(skill.getKey());
	}

	@Override
	public SkillInstance getInstance(Skill skill)
	{
		if(!hasSkill(skill))
		{
			attachSkill(skill);
		}
		return skills.get(skill.getKey());
	}

	@Override
	public boolean attachSkill(Skill skill)
	{
		if(hasSkill(skill))
		{
			return false;
		}
		skills.put(skill.getKey(), skill.newInstance(this));
		return true;
	}

	@Override
	public NBTTagList serializeNBT()
	{
		NBTTagList list = new NBTTagList();
		for(SkillInstance s : skills.values())
		{
			list.appendTag(s.serializeNBT());
		}
		return list;
	}

	@Override
	public void deserializeNBT(NBTTagList list)
	{
		for(int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound nbt = list.getCompoundTagAt(i);
			Skill s = SkillRegistry.getSkill(nbt.getString(AbilitiesAPIConstants.KEY_SKILL_ID));
			getInstance(s).deserializeNBT(nbt);
		}
	}

}
