package alpvax.abilities.api.affected;

import java.util.List;

import com.google.common.base.Predicate;

import alpvax.abilities.api.capabilities.IKeyedCapability;
import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.effect.IAbilityEffect;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * All instances must call {@link CapabilityAbilityHandler#register(this)} in order for them to work properly
 * @author Alpvax
 */
public interface IAbilityAffected extends IKeyedCapability, INBTSerializable<NBTTagList>
{
	public Object getAffected();

	public void add(EffectInstance effect);

	public void remove(EffectInstance effect);

	public void removeAll(Predicate<EffectInstance> filter);

	public List<EffectInstance> getEffects();

	public boolean hasEffect(Predicate<IAbilityEffect> filter);

	/**
	 * Called every game tick, use it to tick all effects.
	 */
	public void tick();
}
