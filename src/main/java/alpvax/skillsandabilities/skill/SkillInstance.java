package alpvax.skillsandabilities.skill;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import alpvax.skillsandabilities.character.ICharacter;
import alpvax.skillsandabilities.core.CharacterModConstants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.INBTSerializable;

public class SkillInstance implements INBTSerializable<NBTTagCompound>
{
	private final Skill skill;
	private final ICharacter character;
	private double baseValue;
	private double value;
	private boolean needsUpdate = true;
	private Map<UUID, SkillModifier> modifiers = new HashMap<>();
	//TODO:Add milestones: private MilestoneMap milestones;

	public SkillInstance(Skill skill, ICharacter character)
	{
		this.skill = skill;
		baseValue = skill.getBaseValue(character);
		this.character = character;
		//milestones = new MilestoneMap(skill);
	}

	public double getBaseValue()
	{
		return baseValue;
	}

	public double getLevel()
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
			switch(m.getOperation())
			{
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

	public void markRequiresUpdate()
	{
		needsUpdate = true;
		/*for(SkillMilestone m : skill.getMilestones())
		{
			IMilestoneState<?> state = milestones.get(m.getKey());
			state.update(handler);
			m.onValueUpdate(handler, state.isAchieved());
		}
		}
		
		public List<SkillMilestone> getAchievedMilestones()
		{
		List<SkillMilestone> list = new ArrayList<>();
		for(IMilestoneState<?> state : milestones)
		{
			if(state.isAchieved())
			{
				list.add(state.getMilestone());
			}
		}
		return list;
		}
		
		public boolean hasAchieved(String milestoneKey)
		{
		return milestones.get(milestoneKey).isAchieved();*/
	}
		
	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString(CharacterModConstants.KEY_SKILL_ID, skill.getKey());
		//nbt.setDouble(CharacterModConstants.KEY_SKILL_BASE, baseValue);
		//TODO:nbt.setTag(CharacterModConstants.KEY_SKILL_MILESTONES, milestones.serializeNBT());
		NBTTagList modList = new NBTTagList();
		for(SkillModifier m : modifiers.values())
		{
			modList.appendTag(m.serializeNBT());
		}
		nbt.setTag(CharacterModConstants.KEY_SKILL_MODIFIERS, modList);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		//baseValue = nbt.getDouble(CharacterModConstants.KEY_SKILL_BASE);
		//TODO:milestones.deserializeNBT(nbt.getCompoundTag(CharacterModConstants.KEY_SKILL_MILESTONES));
		NBTTagList modList = nbt.getTagList(CharacterModConstants.KEY_SKILL_MODIFIERS, NBT.TAG_COMPOUND);
		for(int i = 0; i < modList.tagCount(); i++)
		{
			addModifier(SkillModifier.loadFromNBT(modList.getCompoundTagAt(i)), false);
		}
	}
}
