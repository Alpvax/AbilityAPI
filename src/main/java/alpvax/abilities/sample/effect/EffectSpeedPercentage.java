package alpvax.abilities.sample.effect;

import alpvax.abilities.affected.IAbilityAffected;
import alpvax.abilities.api.IAbilityEffect;
import alpvax.abilities.api.IAbilityHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class EffectSpeedPercentage implements IAbilityEffect
{
	private float amount;

	/**
	 * @param modifier the amount to change the speed by. a value of 1 will be no effect, 0 will disable all movement,
	 *            and 2 will double it etc.
	 */
	public EffectSpeedPercentage(float modifier)
	{
		amount = modifier - 1F;
	}

	@Override
	public void trigger(IAbilityHandler abilityhandler, IAbilityAffected target)
	{
		if(target.getAffected() instanceof EntityLivingBase)
		{
			EntityLivingBase affected = (EntityLivingBase)target.getAffected();
			IAttributeInstance speed = affected.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
			speed.applyModifier(new AttributeModifier(idIn, nameIn, amount, 2));// Apply percentage based modifier
			// TODO: pass EffectState, get UUID from effectState,
		}
	}

	@Override
	public void reset(IAbilityHandler abilityhandler, IAbilityAffected target)
	{
		if(target.getAffected() instanceof EntityLivingBase)
		{
			EntityLivingBase affected = (EntityLivingBase)target.getAffected();
			IAttributeInstance speed = affected.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
			speed.removeModifier(speed.getModifier(uuid));
			// TODO: pass EffectState, get UUID from effectState,
		}
	}

	@Override
	public void tick(IAbilityHandler abilityhandler, IAbilityAffected target)
	{}

}
