package alpvax.abilities.core;

import alpvax.abilities.api.provider.IAbilityProvider;
import alpvax.abilities.api.provider.SimpleAbilityProviderFactory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityAbilityHandler
{
	/*TODO:@CapabilityInject(IAbilityHandler.class)
	public static Capability<IAbilityHandler> ABILITY_HANDLER_CAPABILITY = null;*/

	/*TODO:@CapabilityInject(IAbilityAffected.class)
	public static Capability<IAbilityAffected> ABILITY_AFFECTED_CAPABILITY = null;*/

	@CapabilityInject(IAbilityProvider.class)
	public static Capability<IAbilityProvider> ABILITY_PROVIDER_CAPABILITY = null;

	public static void register()
	{
		/*CapabilityManager.INSTANCE.register(IAbilityHandler.class, new Capability.IStorage<IAbilityHandler>()
		{
			@Override
			public NBTBase writeNBT(Capability<IAbilityHandler> capability, IAbilityHandler instance, EnumFacing side)
			{
				return null;
			}

			@Override
			public void readNBT(Capability<IAbilityHandler> capability, IAbilityHandler instance, EnumFacing side,
					NBTBase base)
			{}
		}, new Callable<IAbilityHandler>()
		{
			@Override
			public IAbilityHandler call() throws Exception
			{
				return null;// TODO: new IAbilityHandler();
			}
		});*/
		/*CapabilityManager.INSTANCE.register(IAbilityAffected.class, new Capability.IStorage<IAbilityAffected>()
		{
			@Override
			public NBTBase writeNBT(Capability<IAbilityAffected> capability, IAbilityAffected instance, EnumFacing side)
			{
				return null;
			}

			@Override
			public void readNBT(Capability<IAbilityAffected> capability, IAbilityAffected instance, EnumFacing side,
					NBTBase base)
			{}
		}, new Callable<IAbilityAffected>()
		{
			@Override
			public IAbilityAffected call() throws Exception
			{
				return null;// TODO: new IAbilityAffected();
			}
		});*/
		CapabilityManager.INSTANCE.register(IAbilityProvider.class, new Capability.IStorage<IAbilityProvider>()
		{
			@Override
			public NBTBase writeNBT(Capability<IAbilityProvider> capability, IAbilityProvider instance, EnumFacing side)
			{
				return null;
			}

			@Override
			public void readNBT(Capability<IAbilityProvider> capability, IAbilityProvider instance, EnumFacing side,
					NBTBase base)
			{}
		}, new SimpleAbilityProviderFactory());
	}
}