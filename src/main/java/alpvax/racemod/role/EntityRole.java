package alpvax.racemod.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import alpvax.abilities.api.ability.Ability;
import alpvax.abilities.api.provider.IAbilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagList;

public class EntityRole implements IAbilityProvider
{
	private static final Map<String, Factory> registeredRoles = new HashMap<>();

	public static abstract class Factory
	{
		public Factory(String key)
		{
			registeredRoles.put(key, this);
		}

		public EntityRole newInstance(Entity entity)
		{
			EntityRole r = new EntityRole(entity);
			r.abilities = makeAbilities();
			return r;
		}

		public abstract List<Ability> makeAbilities();
	}

	private final Entity entity;
	public List<Ability> abilities;

	protected EntityRole(Entity e)
	{
		entity = e;
	}

	public Entity getEntity()
	{
		return entity;
	}

	@Override
	public NBTTagList serializeNBT()
	{
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
		return "race";
	}

	@Override
	public UUID getID()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ability> getAbilities()
	{
		return abilities;
	}

	@Override
	public void tick()
	{
		for(Ability a : abilities)
		{
			a.tick();
		}
	}
}
