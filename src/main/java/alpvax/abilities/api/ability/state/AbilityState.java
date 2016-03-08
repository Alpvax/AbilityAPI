package alpvax.abilities.api.ability.state;

import com.google.common.base.Predicate;

import alpvax.abilities.api.EffectStateInstance;
import alpvax.abilities.api.IAbilityEffect;
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
	private int ticksSinceTriggered;
	private int maxCooldown;
	private int cooldown;
	
	private EffectStateInstance[] effects;
	
	public AbilityState(IAbilityProvider provider)
	{
		this.provider = provider;
		effects = createEffects();
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
		return ticksSinceTriggered > 0;
	}

	public void tick()
	{
		if(isActive())
		{
			ticksSinceTriggered++;
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
			for(EffectStateInstance e : effects)
			{
				e.trigger(this);
			}
		}
	}
	public void reset()
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

	public int ticksSinceTriggered()
	{
		return ticksSinceTriggered;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger(KEY_ACTIVE, ticksSinceTriggered);
		nbt.setInteger(KEY_COOLDOWN_MAX, maxCooldown);
		nbt.setInteger(KEY_COOLDOWN, cooldown);
		if(effects != null && effects.length > 0)
		{
			NBTTagList nbtlist = new NBTTagList();
			for(int i = 0; i < effects.length; i++)
			{
				nbtlist.appendTag(effects[i].serializeNBT());
			}
			nbt.setTag(KEY_EFFECTS, nbtlist);
		}
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		ticksSinceTriggered = nbt.getInteger(KEY_ACTIVE);
		maxCooldown = nbt.getInteger(KEY_COOLDOWN_MAX);
		cooldown = nbt.getInteger(KEY_COOLDOWN);
		if(effects != null && effects.length > 0 && nbt.hasKey(KEY_EFFECTS, NBT.TAG_LIST))
		{
			NBTTagList nbtlist = nbt.getTagList(KEY_EFFECTS, NBT.TAG_COMPOUND);
			for(int i = 0; i < nbtlist.tagCount(); i++)
			{
				effects[i].deserializeNBT(nbtlist.getCompoundTagAt(i));
			}
		}
	}

	public boolean hasEffect(Predicate<IAbilityEffect> filter)
	{
		for(EffectStateInstance e : effects)
		{
			if(filter.apply(e.effect))
			{
				return true;
			}
		}
		return false;
	}
	
	public abstract EffectStateInstance[] createEffects();
}
