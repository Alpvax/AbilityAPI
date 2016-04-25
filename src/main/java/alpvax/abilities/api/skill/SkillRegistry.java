package alpvax.abilities.api.skill;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.fml.common.FMLLog;

public class SkillRegistry
{
	private static Map<String, Skill> registeredSkills = new HashMap<>();

	public static boolean register(Skill skill)
	{
		String id = skill.getKey();
		if(registeredSkills.containsKey(id))
		{
			FMLLog.info("There is already a skill registered with the id \"%s\", using that skill", id);
			return false;
		}
		registeredSkills.put(id, skill);
		return true;
	}

	public static boolean registerOrAddMilestones(Skill skill)
	{
		String id = skill.getKey();
		if(!registeredSkills.containsKey(id))
		{
			return register(skill);
		}
		Skill skill1 = getSkill(id);
		for(SkillMilestone m : skill.getMilestones())
		{
			skill1.addMilestone(m);
		}
		return false;
	}

	public static Skill getSkill(String id)
	{
		return registeredSkills.get(id);
	}
}
