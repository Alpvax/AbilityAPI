package alpvax.abilities.affected;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.common.base.Predicate;

import alpvax.abilities.api.EffectStateInstance;
import alpvax.abilities.api.IAbilityEffect;
import alpvax.abilities.core.AbilitiesAPIConstants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class AbilityAffectedBase implements IAbilityAffected
{
	private Map<UUID, EffectInstance> effects = new HashMap<>();

	@Override
	public NBTTagList serializeNBT()
	{
		NBTTagList list = new NBTTagList();
		for(Map.Entry<UUID, EffectInstance> e : effects.entrySet())
		{
			NBTTagCompound nbt = e.getValue().serializeNBT();
			nbt.setString(AbilitiesAPIConstants.KEY_ABILITY_ID, e.getKey().toString());
			list.appendTag(nbt);
		}
		return list;
	}

	@Override
	public void deserializeNBT(NBTTagList list)
	{
		for(int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound nbt = list.getCompoundTagAt(i);
			effects.get(nbt.getString(AbilitiesAPIConstants.KEY_ABILITY_ID)).deserializeNBT(nbt);
		}
	}

	@Override
	public boolean hasEffect(Predicate<IAbilityEffect> filter)
	{
		for(EffectInstance e : effects.values())
		{
			if(filter.apply(e.effect))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void tick()
	{
		for(EffectInstance e : effects.values())
		{

		}
	}

	@Override
	public void addEffect(EffectStateInstance effect)
	{
		// TODO: get UUID from effect instance, not provider id
	}

}
