package alpvax.abilities.api.handler;

import java.util.ArrayList;
import java.util.List;

import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.provider.IAbilityProvider;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class SimpleAbilityHandler implements IAbilityHandler
{
	private Object handler;
	private List<IAbilityProvider> providers = new ArrayList<>();

	public SimpleAbilityHandler(Object handler)
	{
		setHandler(handler);
	}

	public void setHandler(Object handler)
	{
		this.handler = handler;
	}

	@Override
	public Object getHandler()
	{
		return handler;
	}

	@Override
	public NBTTagList serializeNBT()
	{
		return (NBTTagList)CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY, this, null);
	}

	@Override
	public void deserializeNBT(NBTTagList nbt)
	{
		CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityAbilityHandler.ABILITY_HANDLER_CAPABILITY, this, null, nbt);
	}

	@Override
	public void add(IAbilityProvider provider)
	{
		providers.add(provider);
	}

	@Override
	public void remove(IAbilityProvider provider)
	{
		providers.remove(provider);
	}

	@Override
	public List<IAbilityProvider> getAbilityProviders()
	{
		return new ArrayList<>(providers);
	}

	@Override
	public World getWorld()
	{
		return DimensionManager.getWorld(0);//Return overworld
	}

	@Override
	public Vec3d getPositionVector()
	{
		return new Vec3d(0D, 0D, 0D);//Return origin
	}
}
