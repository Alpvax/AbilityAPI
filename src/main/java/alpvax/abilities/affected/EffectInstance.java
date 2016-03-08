package alpvax.abilities.affected;

import alpvax.abilities.api.IAbilityEffect;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class EffectInstance implements INBTSerializable<NBTTagCompound>
{
	public final IAbilityEffect effect;
	
	private int ticksActive = 0;
	private int maxDuration;
	
	public EffectInstance(IAbilityAffected affected, IAbilityEffect effect, int maxDuration)
	{
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
	
	public void tick()
	{
		if(isActive())
		{
			ticksActive++;
			if(ticksActive >= maxDuration)
			{
				reset();
			}
			//TODO:effect.tick(abilityhandler, target);
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
