package alpvax.abilities.api.affected;

import java.util.List;
import java.util.UUID;

import com.google.common.base.Predicate;

import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.effect.IAbilityEffect;
import alpvax.abilities.api.util.IKeyedEntry;
import alpvax.abilities.api.util.IMCObject;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * All instances must call {@link CapabilityAbilityHandler#register(this)} in order for them to work properly
 * @author Alpvax
 */
public interface IAbilityAffected extends IKeyedEntry<UUID>, IMCObject, INBTSerializable<NBTTagList>
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
