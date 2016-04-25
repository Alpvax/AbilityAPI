package alpvax.abilities.api.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import alpvax.abilities.core.AbilitiesAPIConstants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.INBTSerializable;

public class SkillInstance implements INBTSerializable<NBTTagCompound>
{
	private final Skill skill;
	private final ISkillHandler handler;
	private double baseValue;
	private double value;
	private boolean needsUpdate = true;
	private Map<UUID, SkillModifier> modifiers = new HashMap<>();
	private List<SkillMilestone> achieved = new ArrayList<>();

	public SkillInstance(Skill skill, ISkillHandler handler)
	{
		this.skill = skill;
		baseValue = skill.getBaseValue();
		this.handler = handler;
	}

	public double getBaseValue()
	{
		return baseValue;
	}

	public double getValue()
	{
		if(needsUpdate)
		{
			value = calculateValue();
		}
		return value;
	}

	private double calculateValue()
	{
		double add1 = 0;
		double mult1 = 0;
		double mult2 = 1;
		double add2 = 0;
		for(SkillModifier m : modifiers.values())
		{
			switch(m.getOperation()){
			case ADDITION_BASE:
				add1 += m.getModifier();
				break;
			case MULTIPLICATIVE_SUM:
				mult1 += m.getModifier();
				break;
			case MULTIPLICATIVE_PRODUCT:
				mult2 *= m.getModifier();
				break;
			case ADDITION_FINAL:
				add2 += m.getModifier();
				break;
			}
		}
		needsUpdate = false;
		return (baseValue + add1) * mult1 * mult2 + add2;
	}

	/**
	 * Add a modifier to this skill
	 * @param modifier the modifier to add
	 * @param shouldStack whether or not this modifier should stack (true) or replace (false) any existing modifier with
	 *            the same id.<br>
	 *            If it stacks, it will take the previous modifier's operation.
	 */
	public void addModifier(SkillModifier modifier, boolean shouldStack)
	{
		UUID id = modifier.getKey();
		if(shouldStack && modifiers.containsKey(id))
		{
			SkillModifier m = modifiers.get(id);
			m.setModifier(m.getModifier() + modifier.getModifier());
		}
		else
		{
			modifiers.put(id, modifier);
		}
	}

	public SkillModifier removeModifier(UUID id)
	{
		return modifiers.remove(id);
	}

	protected void markRequiresUpdate()
	{
		needsUpdate = true;
		for(SkillMilestone m : skill.getMilestones())
		{
			if(m.onValueUpdate(handler, getAchievedMilestones().contains(m)))
			{
				achieved.add(m);
			}
			else
			{
				achieved.remove(m);
			}
		}
	}

	public List<SkillMilestone> getAchievedMilestones()
	{
		return achieved;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString(AbilitiesAPIConstants.KEY_SKILL_ID, skill.getKey());
		nbt.setDouble(AbilitiesAPIConstants.KEY_SKILL_BASE, baseValue);
		NBTTagList mileList = new NBTTagList();
		for(SkillMilestone m : getAchievedMilestones())
		{

		}
		nbt.setTag(AbilitiesAPIConstants.KEY_SKILL_MILESTONES, mileList);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		// TODO Auto-generated method stub

	}
}
