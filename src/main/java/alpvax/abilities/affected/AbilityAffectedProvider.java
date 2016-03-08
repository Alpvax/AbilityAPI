package alpvax.abilities.affected;

import alpvax.abilities.core.CapabilityAbilityHandler;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class AbilityAffectedProvider implements ICapabilitySerializable<NBTTagList>
{
	public static class Entity extends AbilityAffectedProvider
	{
		public Entity(net.minecraft.entity.Entity e)
		{
			super(new EntityAffected(e));
		}

	}

	private IAbilityAffected handler;
	
	public AbilityAffectedProvider(IAbilityAffected affected)
	{
		handler = affected;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == CapabilityAbilityHandler.ABILITY_AFFECTED_CAPABILITY ? (T)handler : null;
	}

	@Override
	public NBTTagList serializeNBT()
	{
		return handler.serializeNBT();
	}

	@Override
	public void deserializeNBT(NBTTagList nbt)
	{
		handler.deserializeNBT(nbt);
	}

}
