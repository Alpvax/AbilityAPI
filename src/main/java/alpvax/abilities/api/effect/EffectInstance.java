package alpvax.abilities.api.effect;

import java.util.UUID;

import alpvax.abilities.api.affected.IAbilityAffected;
import alpvax.abilities.api.provider.IAbilityProvider;
import alpvax.abilities.core.AbilitiesAPIConstants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class EffectInstance implements INBTSerializable<NBTTagCompound>
{
	private final EffectTemplate template;
	private final IAbilityProvider provider;
	private final IAbilityAffected affected;

	protected int ticksActive = 0;
	/** The number of game ticks since the effect was triggered */
	public int ticksSinceLast = 0;

	public EffectInstance(IAbilityAffected affected, IAbilityProvider provider, EffectTemplate template)
	{
		this.affected = affected;
		this.provider = provider;
		this.template = template;
	}

	public boolean shouldTick()
	{
		return isActive() && template.shouldTick(this, affected);
	}

	public IAbilityEffect getEffect()
	{
		return template.effect;
	}

	public boolean isActive()
	{
		return ticksActive > 0;
	}

	public void onAttach()
	{
		if(template.shouldTrigger(this, affected))
		{
			template.effect.trigger(provider, affected);
		}
	}

	public void onDetach()
	{
		template.effect.reset(provider, affected);
	}

	public boolean persistAcrossDeath()
	{
		return template.persistAcrossDeath();
	}

	public void tick()
	{
		ticksActive++;
		ticksSinceLast++;
		if(shouldTick())
		{
			getEffect().tick(provider, affected);
		}
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = getEffect().serializeNBT();
		if(nbt == null)
		{
			nbt = new NBTTagCompound();
		}
		UUID id = provider.getID();
		nbt.setLong(AbilitiesAPIConstants.KEY_PROVIDER_MOST, id.getMostSignificantBits());
		nbt.setLong(AbilitiesAPIConstants.KEY_PROVIDER_LEAST, id.getLeastSignificantBits());
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{

	}
}
