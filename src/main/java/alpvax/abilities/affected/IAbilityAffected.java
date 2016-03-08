package alpvax.abilities.affected;

import com.google.common.base.Predicate;

import alpvax.abilities.api.IAbilityEffect;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAbilityAffected extends INBTSerializable<NBTTagList>
{
	public boolean hasEffect(Predicate<IAbilityEffect> filter);
	/**
	 * Used to tick each effect and reset if necessary.
	 */
	public void tick();
}
