package alpvax.racemod.race;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import alpvax.abilities.api.ability.Ability;
import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.provider.IAbilityProvider;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagList;

public class EntityRace implements IAbilityProvider
{
	private static final Map<String, Factory> registeredRaces = new HashMap<>();

	public static EntityRace newInstance(String key, Entity entity)
	{
		Factory f = registeredRaces.get(key);
		return f == null ? null : f.newInstance(entity);
	}

	public static abstract class Factory
	{
		private final String registryKey;

		public Factory(String key)
		{
			registryKey = key;
			registeredRaces.put(registryKey, this);
		}

		public EntityRace newInstance(Entity entity)
		{
			EntityRace r = new EntityRace(registryKey, entity);
			r.abilities = makeAbilities();
			return r;
		}

		public abstract List<Ability> makeAbilities();
	}

	private final String registryKey;
	private UUID id = UUID.randomUUID();
	private final Entity entity;
	private List<Ability> abilities;

	protected EntityRace(String key, Entity e)
	{
		registryKey = key;
		entity = e;
		CapabilityAbilityHandler.register(this);
	}

	public Entity getEntity()
	{
		return entity;
	}

	public String getRaceID()
	{
		return registryKey;
	}

	@Override
	public UUID getKey()
	{
		return id;
	}

	@Override
	public NBTTagList serializeNBT()
	{
		NBTTagList nbt = new NBTTagList();
		for(Ability a : abilities)
		{
			nbt.appendTag(a.serializeNBT());
		}
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagList nbt)
	{
		int i = 0;
		for(Ability a : abilities)
		{
			a.deserializeNBT(nbt.getCompoundTagAt(i++));
		}
	}

	@Override
	public String getAttachKey()
	{
		return "race";
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

	@Override
	public String getDisplayName()
	{
		return I18n.format("race." + getRaceID() + ".name");
	}
}