package alpvax.abilities.api;

import com.google.common.base.Predicate;

import alpvax.abilities.api.ability.IAbilityState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public interface IAbilityHandler
{
	public IAbilityState getAbilities(Predicate<IAbilityState> filter);
	
	public Vec3 getPositionVector();
	public BlockPos getPosition();
	
	public double getDistanceSq(double x, double y, double z);
}
