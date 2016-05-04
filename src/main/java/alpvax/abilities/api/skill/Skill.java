package alpvax.abilities.api.skill;

import java.util.ArrayList;
import java.util.List;

import alpvax.abilities.api.util.IKeyedEntry;

public abstract class Skill implements IKeyedEntry<String>
{
	private String id;
	private List<SkillMilestone> milestones = new ArrayList<>();

	public Skill(String key)
	{
		id = key;
	}

	public SkillInstance newInstance(ISkillHandler handler)
	{
		return new SkillInstance(this, handler);
	}

	protected abstract double getBaseValue(ISkillHandler handler);

	@Override
	public String getKey()
	{
		return id;
	}

	public List<SkillMilestone> getMilestones()
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
	}

	@Override
	public void setKey(String key)
	{
		id = key;
	}
}
