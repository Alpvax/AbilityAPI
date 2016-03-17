package alpvax.abilities.api;

import java.util.List;

import com.google.common.base.Predicate;

import alpvax.abilities.api.ability.state.AbilityState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public interface IAbilityHandler
{
	public List<AbilityState> getAbilities(Predicate<AbilityState> filter);

	public List<IAbilityProvider> getProviders();

	public IAbilityProvider getProvider(String id);

	public void addAbilities(IAbilityProvider provider);

	public Vec3 getPositionVector();

	public BlockPos getPosition();

	public double getDistanceSq(double x, double y, double z);

}
