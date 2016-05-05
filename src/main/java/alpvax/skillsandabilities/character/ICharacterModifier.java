package alpvax.skillsandabilities.character;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import alpvax.skillsandabilities.ability.IAbility;
import alpvax.skillsandabilities.skill.Skill;
import alpvax.skillsandabilities.skill.SkillModifier;

public interface ICharacterModifier
{
	public UUID getID();

	public List<IAbility> getAbilities();

	public Map<Skill, SkillModifier> getSkillModifiers();
}
