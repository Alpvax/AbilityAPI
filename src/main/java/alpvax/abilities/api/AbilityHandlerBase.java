package alpvax.abilities.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;

import alpvax.abilities.api.ability.state.AbilityState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public abstract class AbilityHandlerBase implements IAbilityHandler
{
	private Map<String, IAbilityProvider> providers = new HashMap<>();


	@Override
	public List<AbilityState> getAbilities(Predicate<AbilityState> filter)
	{
		List<AbilityState> abilities = new ArrayList<>();
		for(IAbilityProvider p : getProviders())
		{
			abilities.addAll(p.getAbilities(filter));
		}
		return abilities;
	}

	@Override
	public List<IAbilityProvider> getProviders()
	{
		return new ArrayList<>(providers.values());
	}

	@Override
	public IAbilityProvider getProvider(String id)
	{
		return providers.get(id);
	}

	@Override
	public void addAbilities(IAbilityProvider provider)
	{
		providers.put(provider.getID(), provider);
	}

	@Override
	public Vec3 getPositionVector()
	{
		BlockPos pos = getPosition();
		return new Vec3((pos.getX()) + 0.5D, (pos.getY()) + 0.5D, (pos.getZ()) + 0.5D);
	}

	@Override
	public double getDistanceSq(double x, double y, double z)
	{
		return getPositionVector().squareDistanceTo(new Vec3(x, y, z));
	}

}
