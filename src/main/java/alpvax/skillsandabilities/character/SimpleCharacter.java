package alpvax.skillsandabilities.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import alpvax.skillsandabilities.ability.IAbility;
import alpvax.skillsandabilities.skill.Skill;
import alpvax.skillsandabilities.skill.SkillInstance;
import alpvax.skillsandabilities.skill.SkillModifier;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class SimpleCharacter implements ICharacter
{
	private ICapabilityProvider capabilityProvider;
	private Map<UUID, ICharacterModifier> modifiers = new HashMap<>();
	private Map<String, SkillInstance> skills = new HashMap<>();
	private Map<UUID, IAbility> abilities = new HashMap<>();

	public SimpleCharacter(ICapabilityProvider attached)
	{
		capabilityProvider = attached;
	}

	@Override
	public ICapabilityProvider getAttachedObject()
	{
		return capabilityProvider;
	}

	@Override
	public boolean hasSkill(Skill skill)
	{
		return skills.containsKey(skill.getKey());
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
	public SkillInstance getSkill(Skill skill)
	{
		if(!hasSkill(skill))
		{
			attachSkill(skill);
		}
		return skills.get(skill.getKey());
	}

	@Override
	public List<IAbility> getAbilities()
	{
		return new ArrayList<>(abilities.values());
	}

	@Override
	public void add(ICharacterModifier modifier)
	{
		for(Map.Entry<Skill, SkillModifier> e : modifier.getSkillModifiers().entrySet())
		{
			getSkill(e.getKey()).addModifier(e.getValue(), true);
		}
		for(IAbility a : modifier.getAbilities())
		{
			abilities.put(a.getID(), a);
		}
		modifiers.put(modifier.getID(), modifier);
	}

	@Override
	public void remove(UUID modifierID)
	{
		ICharacterModifier modifier = modifiers.remove(modifierID);
		for(Map.Entry<Skill, SkillModifier> e : modifier.getSkillModifiers().entrySet())
		{
			getSkill(e.getKey()).removeModifier(e.getValue().getKey());
		}
		for(IAbility a : modifier.getAbilities())
		{
			abilities.remove(a.getID());
		}
		modifiers.remove(modifier.getID());
	}

}
