package alpvax.abilities.core;

import java.util.concurrent.Callable;

import alpvax.abilities.affected.IAbilityAffected;
import alpvax.abilities.api.provider.IAbilityProvider;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityAbilityHandler
{
    @CapabilityInject(IAbilityProvider.class)
    public static Capability<IAbilityProvider> ABILITY_PROVIDER_CAPABILITY = null;
    
    @CapabilityInject(IAbilityAffected.class)
    public static Capability<IAbilityAffected> ABILITY_AFFECTED_CAPABILITY = null;
    
    public static void register()
    {
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
        }, new Callable<IAbilityProvider>()
        {
            @Override
            public IAbilityProvider call() throws Exception
            {
                return null;//TODO:URGENT:new IAbilityProvider();
            }
        });
    	CapabilityManager.INSTANCE.register(IAbilityAffected.class, new Capability.IStorage<IAbilityAffected>()
        {
            @Override
            public NBTBase writeNBT(Capability<IAbilityAffected> capability, IAbilityAffected instance, EnumFacing side)
            {
            	return null;
            }

            @Override
            public void readNBT(Capability<IAbilityAffected> capability, IAbilityAffected instance, EnumFacing side, NBTBase base)
            {
            }
        }, new Callable<IAbilityAffected>()
        {
            @Override
            public IAbilityAffected call() throws Exception
            {
                return null;//TODO:URGENT:new IAbilityAffected();
            }
        });
    }
}
