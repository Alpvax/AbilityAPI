package alpvax.abilities.api.capabilities;

import java.util.concurrent.Callable;

import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.affected.SimpleAbilityAffected;
import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.provider.IAbilityProvider;
import alpvax.abilities.api.provider.SimpleAbilityProviderFactory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityAbilityHandler
{
	/*TODO:@CapabilityInject(IAbilityHandler.class)
	public static Capability<IAbilityHandler> ABILITY_HANDLER_CAPABILITY = null;*/

	@CapabilityInject(IAbilityAffected.class)
	public static Capability<IAbilityAffected> ABILITY_AFFECTED_CAPABILITY = null;

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
			public void readNBT(Capability<IAbilityHandler> capability, IAbilityHandler instance, EnumFacing side, NBTBase base)
			{

			}
		}, new Callable<IAbilityHandler>()
		{
			@Override
			public IAbilityHandler call() throws Exception
			{
				return null;// TODO: new IAbilityHandler();
			}
		});*/
		CapabilityManager.INSTANCE.register(IAbilityAffected.class, new Capability.IStorage<IAbilityAffected>()
		{
			@Override
			public NBTBase writeNBT(Capability<IAbilityAffected> capability, IAbilityAffected instance, EnumFacing side)
			{
				NBTTagList list = new NBTTagList();
				for(EffectInstance e : instance.getEffects())
				{
					list.appendTag(e.serializeNBT());
				}
				return list;
			}

			@Override
			public void readNBT(Capability<IAbilityAffected> capability, IAbilityAffected instance, EnumFacing side, NBTBase base)
			{
				//TODO:Read
			}
		}, new Callable<IAbilityAffected>()
		{
			@Override
			public IAbilityAffected call() throws Exception
			{
				return new SimpleAbilityAffected(null);
			}
		});
		CapabilityManager.INSTANCE.register(IAbilityProvider.class, new Capability.IStorage<IAbilityProvider>()
		{
			@Override
			public NBTBase writeNBT(Capability<IAbilityProvider> capability, IAbilityProvider instance, EnumFacing side)
			{
				return null;
			}

			@Override
			public void readNBT(Capability<IAbilityProvider> capability, IAbilityProvider instance, EnumFacing side, NBTBase base)
			{

			}
		}, new SimpleAbilityProviderFactory());
	}
}