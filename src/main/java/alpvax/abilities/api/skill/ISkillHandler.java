package alpvax.abilities.api.skill;

import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISkillHandler extends INBTSerializable<NBTTagList>
{
	/**
	 * Check whether the skill has been attached to this handler
	 * @param skill the skill to check
	 */
	public boolean hasSkill(Skill skill);

	/**
	 * Attempt to attach a skill to the handler.
	 * @param skill the skill to attach a new instance of.
	 * @return true if it succeeded (i.e. the skill wasn't already attached)
	 */
	public boolean attachSkill(Skill skill);

	/**
	 * Used to retrieve the instance of the skill.<br>
	 * Implementations should attach the skill if it doesn't exist.
	 * @param key the id of the skill to retrieve the instance of.
	 * @return the instance of the skill
	 */
	public SkillInstance getInstance(Skill skill);
}
