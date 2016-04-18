package alpvax.abilities.api.provider;

import java.util.List;
import java.util.UUID;

import alpvax.abilities.api.ability.Ability;
import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import alpvax.abilities.api.capabilities.ICapabilityTickable;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * All implementations of this interface need to call {@link CapabilityAbilityHandler.Registry#registerProvider}
 */
public interface IAbilityProvider extends ICapabilityTickable, INBTSerializable<NBTTagList>
{
	public static interface IAbilityProviderFactory
	{
		public IAbilityProvider newProvider();
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
	 * @return A UUID that can be used in {@link CapabilityAbilityHandler#getProvider}
	 */
	public UUID getID();

	/**
	 * @return A list of abilities matching the filter
	 */
	public List<Ability> getAbilities();

	/**
	 * {@inheritDoc}, use it to tick all abilities.
	 */
	@Override
	public void tick();

	/**
	 * @return The {@link IAbilityHandler} this provider is currently attached to, or null if it isn't currently
	 *         attached
	 */
	//public IAbilityHandler getHandler();
}
