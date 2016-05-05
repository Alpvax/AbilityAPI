package alpvax.skillsandabilities.character;

import java.util.List;
import java.util.UUID;

import alpvax.skillsandabilities.ability.IAbility;
import alpvax.skillsandabilities.skill.Skill;
import alpvax.skillsandabilities.skill.SkillInstance;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public interface ICharacter
{
	/**
	 * @return the object this ICharacter is attached to.
	 */
	public ICapabilityProvider getObject();

	/**
	 * Check whether this ICharacter has an instance of this skill
	 * @param skill the skill to check.
	 */
	public boolean hasSkill(Skill skill);

	/**
	 * Attempt to attach an instance of a skill.
	 * @param skill the skill to attach a new instance of.
	 * @return true if it succeeded (i.e. the skill wasn't already attached).
	 */
	public boolean attachSkill(Skill skill);

	/**
	 * Used to retrieve the instance of the skill.<br>
	 * Implementations should attach the skill if it doesn't exist.
	 * @param the skill to retrieve the instance of.
	 * @return the instance of the skill.
	 */
	public SkillInstance getSkill(Skill skill);

	/**
	 * Shorthand to retrieve the level of the skill.<br>
	 * Default implementation just calls {@link #getSkill(skill)}.{@link SkillInstance#getLevel() getLevel()}.
	 * @param skill the skill to retrieve the level of.
	 * @return the modified level of the given skill for this ICharacter.
	 */
	public default double getLevel(Skill skill)
	{
		return getSkill(skill).getLevel();
	}

	/**
	 * @return a list of all available abilities.
	 */
	public List<IAbility> getAbilities();

	/**
	 * Add a modifier to the character.
	 * @param modifier the modifier to add.
	 */
	public void add(ICharacterModifier modifier);

	/**
	 * Remove a modifier from the character.
	 * @param modifier the id of the modifier to remove.
	 */
	public void remove(UUID modifierID);
}
