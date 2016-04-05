package alpvax.abilities.api.provider;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Throwables;

import alpvax.abilities.api.ability.Ability.IAbilityFactory;
import alpvax.abilities.api.provider.IAbilityProvider.IAbilityProviderFactory;
import alpvax.abilities.provider.SimpleAbilityProvider;

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
	public IAbilityProvider call()
	{
		SimpleAbilityProvider p = new SimpleAbilityProvider();
		for(IAbilityFactory f : abilityfactories)
		{
			try
			{
				p.addAbility(f.call());
			}
			catch(Exception e)
			{
				Throwables.propagate(e);
			}
		}
		return p;
	}

}
