package alpvax.abilities.api.provider;

import java.util.List;
import java.util.concurrent.Callable;

import alpvax.abilities.api.ability.Ability;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAbilityProvider extends INBTSerializable<NBTTagList>
{
	public static interface IAbilityProviderFactory extends Callable<IAbilityProvider>
	{
	};

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
	 * @return A list of abilities matching the filter
	 */
	public List<Ability> getAbilities();

	/**
	 * @return The {@link IAbilityHandler} this provider is currently attached to, or null if it isn't currently
	 *         attached
	 */
	//public IAbilityHandler getHandler();
}
