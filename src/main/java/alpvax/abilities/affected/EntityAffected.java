package alpvax.abilities.affected;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.common.base.Predicate;

import alpvax.abilities.api.EffectStateInstance;
import alpvax.abilities.api.IAbilityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class EntityAffected implements IAbilityAffected
{
	private static final String KEY_ID = "EffectProviderUUID";
	
	private Map<UUID, EffectInstance> effects = new HashMap<>();
	public final Entity affectedEntity;
	
	public EntityAffected(Entity e)
	{
		affectedEntity = e;
	}

	@Override
	public NBTTagList serializeNBT()
	{
		NBTTagList list = new NBTTagList();
		for(Map.Entry<UUID, EffectInstance> e : effects.entrySet())
		{
			NBTTagCompound nbt = e.getValue().serializeNBT();
			nbt.setString(KEY_ID, e.getKey().toString());
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
			effects.get(nbt.getString(KEY_ID)).deserializeNBT(nbt);
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
		//TODO: get UUID from effect instance, not provider id
	}
}
