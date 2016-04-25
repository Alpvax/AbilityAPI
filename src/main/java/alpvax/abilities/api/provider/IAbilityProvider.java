package alpvax.abilities.api.provider;

import java.util.List;
import java.util.UUID;

import alpvax.abilities.api.ability.Ability;
import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.util.IKeyedEntry;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * All implementations of this interface need to call {@link CapabilityAbilityHandler.Registry#registerProvider}
 */
public interface IAbilityProvider extends IKeyedEntry<UUID>, INBTSerializable<NBTTagList>
{
	public static abstract class AbilityProviderFactory
	{
		public final IAbilityProvider newProvider()
		{
			return CapabilityAbilityHandler.register(createProvider());
		}

		protected abstract IAbilityProvider createProvider();
	};

	/**
	 * Used for adding and removing providers to handlers. Any providers with the same Key will replace one another.<br>
	 * <br>
	 * One example of this usage would be to return e.g. "race" from here to allow Entities to have a single race.<br>
	 * Item providers should return a unique identifier for each stack, otherwise the last item added will be the one
	 * used.<br>
	 * @return a unique id for the provider to use when added to the handler
	 */
	public String getAttachKey();

	/**
	 * @return the localised name of this provider for use in a GUI.
	 */
	public String getDisplayName();

	/**
	 * @return A list of abilities matching the filter
	 */
	public List<Ability> getAbilities();

	/**
	 * Called every game tick, use it to tick all abilities.
	 */
	public void tick();

	/**
	 * @return this provider, modified if necessary.
	 */
	public IAbilityProvider cloneAcrossDeath();

	/**
	 * @return The {@link IAbilityHandler} this provider is currently attached to, or null if it isn't currently
	 *         attached
	 */
	//public IAbilityHandler getHandler();
}
