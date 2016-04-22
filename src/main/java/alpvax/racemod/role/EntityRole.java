package alpvax.racemod.role;

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

public class EntityRole implements IAbilityProvider
{
	private static final Map<String, Factory> registeredRoles = new HashMap<>();

	public static EntityRole newInstance(String key, Entity entity)
	{
		Factory f = registeredRoles.get(key);
		return f == null ? null : f.newInstance(entity);
	}

	public static abstract class Factory
	{
		private final String registryKey;

		public Factory(String key)
		{
			registryKey = key;
			registeredRoles.put(registryKey, this);
		}

		public EntityRole newInstance(Entity entity)
		{
			EntityRole r = new EntityRole(registryKey, entity);
			r.abilities = makeAbilities();
			return r;
		}

		public abstract List<Ability> makeAbilities();
	}

	private final String registryKey;
	private UUID id = UUID.randomUUID();
	private final Entity entity;
	private List<Ability> abilities;

	protected EntityRole(String key, Entity e)
	{
		registryKey = key;
		entity = e;
		CapabilityAbilityHandler.register(this);
	}

	public Entity getEntity()
	{
		return entity;
	}

	public String getRoleID()
	{
		return registryKey;
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
		return I18n.format("role." + getRoleID() + ".name");
	}

	@Override
	public IAbilityProvider cloneAcrossDeath()
	{
		return this;
	}
}
