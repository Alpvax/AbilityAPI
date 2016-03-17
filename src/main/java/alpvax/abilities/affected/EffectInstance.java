package alpvax.abilities.affected;

import alpvax.abilities.api.IAbilityEffect;
import alpvax.abilities.api.IAbilityHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class EffectInstance implements INBTSerializable<NBTTagCompound>
{
	public final IAbilityEffect effect;
	private IAbilityHandler abilityHandler;
	private IAbilityAffected affected;

	private int ticksActive = 0;
	private int maxDuration;

	public EffectInstance(IAbilityAffected affected, IAbilityHandler source, IAbilityEffect effect, int maxDuration)
	{
		this.affected = affected;
		abilityHandler = source;
		this.effect = effect;
		this.maxDuration = maxDuration;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		// TODO Auto-generated method stub

	}

	protected boolean shouldReset()
	{
		return ticksActive >= maxDuration;
	}

	public void tick()
	{
		if(isActive())
		{
			ticksActive++;
			if(shouldReset())
			{
				reset();
			}
			effect.tick(abilityHandler, affected);
		}
	}

	public boolean isActive()
	{
		return ticksActive >= 0;
	}

	public void trigger()
	{
		ticksActive = 0;
		// TODO Auto-generated method stub
	}

	public void reset()
	{
		ticksActive = -1;
		// TODO Auto-generated method stub
	}
}
