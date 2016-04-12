package alpvax.abilities.api.handler;

import java.util.List;

import alpvax.abilities.api.provider.IAbilityProvider;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAbilityHandler extends INBTSerializable<NBTTagList>
{
	public Object getHandler();

	public void add(IAbilityProvider provider);

	public void remove(IAbilityProvider provider);

	public List<IAbilityProvider> getAbilityProviders();

	public Vec3d getPositionVector();
}
