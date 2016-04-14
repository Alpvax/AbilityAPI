package alpvax.abilities.api.registry;

import java.util.HashMap;
import java.util.Map;

import alpvax.abilities.api.provider.IAbilityProvider.IAbilityProviderFactory;
import net.minecraft.util.ResourceLocation;

public class AbilityAPIRegistry
{
	private final Map<ResourceLocation, IAbilityProviderFactory> providerRegistry = new HashMap<>();
	//private final Map<ResourceLocation, IAbilityProviderFactory> effectRegistry = new HashMap<>();

	public static void registerProvider(ResourceLocation key, IAbilityProviderFactory provider)
	{
		//TODO:Register provider
	}

	/*public static void registerEffect(ResourceLocation key, Class<? extends IAbilityEffect> effectClass)
	{

		//TODO:Register provider
	}

	private <T> Constructor<T> getNoArgsConstructor(Class<T> c) throws InvalidClassException
	{
		try
		{
			return c.getConstructor();
		}
		catch(NoSuchMethodException | SecurityException e)
		{
			throw new InvalidClassException(c.getName(), e instanceof NoSuchMethodException ? "No no-args constructor found" : "No-args constructor isn't public");
		}
	}*/
}
