package alpvax.abilities.api.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import alpvax.abilities.api.ability.Ability;
import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagList;

public class SimpleAbilityProvider implements IAbilityProvider
{
	private UUID id = UUID.randomUUID();
	private List<Ability> abilities = new ArrayList<>();
	private String name;

	public SimpleAbilityProvider(String displayNameKey)
	{
		CapabilityAbilityHandler.register(this);
		name = displayNameKey;
	}

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
	public String getAttachKey()
	{
		return name;
	}

	@Override
	public UUID getKey()
	{
		return id;
	}

	@Override
	public void setKey(UUID id)
	{
		this.id = id;
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

	@Override
	public void tick()
	{
		for(Ability a : abilities)
		{
			a.tick();
		}
	}

	@Override
	public String getDisplayName()
	{
		return I18n.format(name + ".name");
	}

	@Override
	public IAbilityProvider cloneAcrossDeath()
	{
		return this;
	}

}
