package alpvax.abilities.api.provider;

import java.util.ArrayList;
import java.util.List;

import alpvax.abilities.api.ability.Ability.IAbilityFactory;
import alpvax.abilities.api.provider.IAbilityProvider.AbilityProviderFactory;

public class SimpleAbilityProviderFactory extends AbilityProviderFactory
{
	private List<IAbilityFactory> abilityfactories = new ArrayList<>();
	private String name;

	public SimpleAbilityProviderFactory(String displayName)
	{
		name = displayName;
	}

	public SimpleAbilityProviderFactory addAbility(IAbilityFactory ability)
	{
		abilityfactories.add(ability);
		return this;
	}

	@Override
	public IAbilityProvider createProvider()
	{
		SimpleAbilityProvider p = new SimpleAbilityProvider(name);
		for(IAbilityFactory f : abilityfactories)
		{
			p.addAbility(f.newAbility());
		}
		return p;
	}

}
