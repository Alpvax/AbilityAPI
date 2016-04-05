package alpvax.abilities.api.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import alpvax.abilities.api.ability.Ability;
import net.minecraft.nbt.NBTTagList;

public class SimpleAbilityProvider implements IAbilityProvider
{
	private List<Ability> abilities = new ArrayList<>();

	@Override
	public NBTTagList serializeNBT()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagList nbt)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getID()
	{
		return UUID.randomUUID().toString();
	}

	@Override
	public List<Ability> getAbilities()
	{
		return abilities;
	}

	public SimpleAbilityProvider addAbility(Ability ability)
	{
		abilities.add(ability);
		return this;
	}

}
