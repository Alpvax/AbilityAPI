package alpvax.abilities.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

import alpvax.abilities.api.effect.EffectInstance;

public class EffectMap
{
	private Map<String, Map<UUID, EffectInstance>> map = new HashMap<>();

	public List<EffectInstance> effects()
	{
		return effects(new Predicate<EffectInstance>()
		{
			@Override
			public boolean test(EffectInstance i)
			{
				return true;
			}
		});
	}

	public List<EffectInstance> effects(Predicate<EffectInstance> filter)
	{
		List<EffectInstance> l = new ArrayList<>();
		for(Map<UUID, EffectInstance> m : map.values())
		{
			for(EffectInstance i : m.values())
			{
				if(filter.test(i))
				{
					l.add(i);
				}
			}
		}
		return l;
	}

	public void clear()
	{
		// TODO Auto-generated method stub

	}

	public boolean containsKey(Object arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public EffectInstance get(Object arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public Set keySet()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object put(Object arg0, Object arg1)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void putAll(Map arg0)
	{
		// TODO Auto-generated method stub

	}

	public Object remove(Object arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int size()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Collection values()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
