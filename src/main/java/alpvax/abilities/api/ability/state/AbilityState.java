package alpvax.abilities.api.ability.state;

import java.util.List;

import alpvax.abilities.api.EffectInstance;
import alpvax.abilities.api.IAbilityHandler;
import alpvax.abilities.api.IAbilityProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class AbilityState implements INBTSerializable<NBTTagCompound>
{
	public static final String KEY_ACTIVE = "Active";
	public static final String KEY_COOLDOWN = "Cooldown";
	public static final String KEY_COOLDOWN_MAX = "CooldownMax";
	public static final String KEY_EFFECTS = "Effects";
	
	private final IAbilityProvider provider;
	/**
	 * The number of ticks since the power became active.
	 */
	private int ticksActive;
	private int maxCooldown;
	private int cooldown;
	
	public AbilityState(IAbilityProvider provider)
	{
		this.provider = provider;
	}

	public int remainingCooldown()
	{
		return cooldown;
	}

	public boolean shouldTrigger()
	{
		return false;
	}

	public boolean isActive()
	{
		return ticksActive > 0;
	}

	public void tick()
	{
		if(isActive())
		{
			ticksActive++;
		}
		if(cooldown > 0)
		{
			cooldown--;
		}
	}
	public void trigger()
	{
		if(getHandler() != null)
		{
			cooldown += maxCooldown;
		}
	}

	public IAbilityHandler getHandler()
	{
		return provider.getHandler();
	}

	public int ticksActive()
	{
		return ticksActive;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger(KEY_ACTIVE, ticksActive);
		nbt.setInteger(KEY_COOLDOWN_MAX, maxCooldown);
		nbt.setInteger(KEY_COOLDOWN, cooldown);
		List<EffectInstance> list = getEffects();
		if(list != null && list.size() > 0)
		{
			NBTTagList nbtlist = new NBTTagList();
			for(INBTSerializable<NBTTagCompound> effect : list)
			{
				nbtlist.appendTag(effect.serializeNBT());
			}
			nbt.setTag(KEY_EFFECTS, nbtlist);
		}
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		ticksActive = nbt.getInteger(KEY_ACTIVE);
		maxCooldown = nbt.getInteger(KEY_COOLDOWN_MAX);
		cooldown = nbt.getInteger(KEY_COOLDOWN);
		List<EffectInstance> list = getEffects();
		if(list != null && list.size() > 0 && nbt.hasKey(KEY_EFFECTS, NBT.TAG_LIST))
		{
			NBTTagList nbtlist = nbt.getTagList(KEY_EFFECTS, NBT.TAG_COMPOUND);
			for(int i = 0; i < nbtlist.tagCount(); i++)
			{
				list.get(i).deserializeNBT(nbtlist.getCompoundTagAt(i));
			}
		}
	}

	public abstract List<EffectInstance> getEffects();
}
