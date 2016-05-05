package alpvax.skillsandabilities.skill;

import alpvax.skillsandabilities.character.ICharacter;

public abstract class Skill
{
	private String id;
	//TODO:Add milestones:private List<SkillMilestone> milestones = new ArrayList<>();

	public Skill(String key)
	{
		id = key;
	}

	public SkillInstance newInstance(ICharacter character)
	{
		return new SkillInstance(this, character);
	}

	protected abstract double getBaseValue(ICharacter character);

	public String getKey()
	{
		return id;
	}

	/*public List<SkillMilestone> getMilestones()
	{
		return new ArrayList<>(milestones);
	}
	
	public SkillMilestone getMilestone(String key)
	{
		for(SkillMilestone m : getMilestones())
		{
			if(m.getKey().equals(key))
			{
				return m;
			}
		}
		return null;
	}
	
	public void addMilestone(SkillMilestone milestone)
	{
		if(!milestones.contains(milestone))
		{
			milestones.add(milestone);
		}
	}*/

}
