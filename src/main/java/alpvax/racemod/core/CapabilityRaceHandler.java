package alpvax.racemod.core;

import java.util.concurrent.Callable;

import alpvax.abilities.api.capabilities.SerializableCapabilityProvider;
import alpvax.abilities.api.provider.IAbilityProvider;
import alpvax.racemod.race.EntityRaceHandler;
import alpvax.racemod.race.IEntityRace;
import alpvax.racemod.role.EntityRoleHandler;
import alpvax.racemod.role.IEntityRole;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityRaceHandler
{
	@CapabilityInject(IAbilityProvider.class)
	public static Capability<IAbilityProvider> ABILITY_PROVIDER_CAPABILITY = null;

	@CapabilityInject(IEntityRace.class)
	public static Capability<IEntityRace> RACE_CAPABILITY = null;

	public static class CapabilityProviderRace extends SerializableCapabilityProvider<IEntityRace, NBTTagString>
	{

		public CapabilityProviderRace(IEntityRace capabilityHandler)
		{
			super(capabilityHandler, RACE_CAPABILITY);
		}

	}

	@CapabilityInject(IEntityRole.class)
	public static Capability<IEntityRole> ROLE_CAPABILITY = null;

	public static class CapabilityProviderRole extends SerializableCapabilityProvider<IEntityRole, NBTTagString>
	{

		public CapabilityProviderRole(IEntityRole capabilityHandler)
		{
			super(capabilityHandler, ROLE_CAPABILITY);
		}

	}

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IEntityRace.class, new Capability.IStorage<IEntityRace>()
		{
			@Override
			public NBTBase writeNBT(Capability<IEntityRace> capability, IEntityRace instance, EnumFacing side)
			{
				return null;
			}

			@Override
			public void readNBT(Capability<IEntityRace> capability, IEntityRace instance, EnumFacing side, NBTBase base)
			{
			}
		}, new Callable<IEntityRace>()
		{
			@Override
			public IEntityRace call() throws Exception
			{
				return new EntityRaceHandler();
			}
		});
		CapabilityManager.INSTANCE.register(IEntityRole.class, new Capability.IStorage<IEntityRole>()
		{
			@Override
			public NBTBase writeNBT(Capability<IEntityRole> capability, IEntityRole instance, EnumFacing side)
			{
				return null;
			}

			@Override
			public void readNBT(Capability<IEntityRole> capability, IEntityRole instance, EnumFacing side, NBTBase base)
			{
			}
		}, new Callable<IEntityRole>()
		{
			@Override
			public IEntityRole call() throws Exception
			{
				return new EntityRoleHandler();
			}
		});
	}
}
