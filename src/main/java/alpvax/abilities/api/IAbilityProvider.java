package alpvax.abilities.api;

import java.util.List;

import com.google.common.base.Predicate;

import alpvax.abilities.api.ability.state.AbilityState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAbilityProvider extends INBTSerializable<NBTTagCompound>
{
	/**
	 * Used for adding and removing providers to handlers. Any providers with the same ID will replace one another.<br>
	 * <br>
	 * One example of this usage would be to return e.g. "race" from here to allow Entities to have a single race.<br>
	 * Item providers should return a unique identifier for each stack, otherwise the last item added will be the one
	 * used.<br>
	 * TODO: Implement form of stacking for Item providers.
	 * @return a unique id for the provider to use when added to the handler
	 */
	public String getID();

	/**
	 * @param filter the filter that abilities have to match to be returned.
	 * @return A list of abilities matching the filter
	 */
	public List<AbilityState> getAbilities(Predicate<AbilityState> filter);

	/**
	 * @return The {@link IAbilityHandler} this provider is currently attached to, or null if it isn't currently
	 *         attached
	 */
	public IAbilityHandler getHandler();
}
