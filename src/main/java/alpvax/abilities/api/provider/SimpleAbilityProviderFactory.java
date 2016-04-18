package alpvax.abilities.api.provider;

import java.util.ArrayList;
import java.util.List;

import alpvax.abilities.api.ability.Ability.IAbilityFactory;
import alpvax.abilities.api.provider.IAbilityProvider.IAbilityProviderFactory;

public class SimpleAbilityProviderFactory implements IAbilityProviderFactory
{
	private List<IAbilityFactory> abilityfactories = new ArrayList<>();

	public SimpleAbilityProviderFactory()
	{
		// TODO Auto-generated constructor stub
	}

	public SimpleAbilityProviderFactory addAbility(IAbilityFactory ability)
	{
		abilityfactories.add(ability);
		return this;
	}

	@Override
	public IAbilityProvider newProvider()
	{
		SimpleAbilityProvider p = new SimpleAbilityProvider();
		for(IAbilityFactory f : abilityfactories)
		{
			p.addAbility(f.newAbility());
		}
		return p;
	}

}
