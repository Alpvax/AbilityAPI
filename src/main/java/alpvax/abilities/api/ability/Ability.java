package alpvax.abilities.api.ability;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.provider.IAbilityProvider;
import alpvax.abilities.api.util.IUUIDKeyed;
import alpvax.abilities.core.AbilitiesAPIConstants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class Ability implements IUUIDKeyed, INBTSerializable<NBTTagCompound>
{
	public static interface IAbilityFactory
	{
		public Ability newAbility();
	};

	private IAbilityProvider provider;
	private UUID id;

	protected Ability(IAbilityProvider provider)
	{
		this(provider, UUID.randomUUID());
	}

	protected Ability(IAbilityProvider provider, UUID id)
	{
		this.provider = provider;
		setKey(id);
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

	public IAbilityProvider getProvider()
	{
		return provider;
	}

	public void tick()
	{
		//TODO: Implement
	}

	public List<IAbilityAffected> getTargets()
	{
		//TODO: Implement
		return new ArrayList<>();
	}

	public void trigger()
	{
		//TODO: Implement
	}

	public void reset()
	{
		//TODO: Implement
	}

	public boolean isActive()
	{
		//TODO: Implement
		return false;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setLong(AbilitiesAPIConstants.KEY_ID_MOST, id.getMostSignificantBits());
		nbt.setLong(AbilitiesAPIConstants.KEY_ID_LEAST, id.getLeastSignificantBits());
		//TODO:Save effects
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		setKey(new UUID(nbt.getLong(AbilitiesAPIConstants.KEY_ID_MOST), nbt.getLong(AbilitiesAPIConstants.KEY_ID_LEAST)));
		//TODO:Load effects
	}
}