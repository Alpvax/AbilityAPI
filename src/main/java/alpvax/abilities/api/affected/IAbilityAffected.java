package alpvax.abilities.api.affected;

import java.util.List;

import com.google.common.base.Predicate;

import alpvax.abilities.api.capabilities.ICapabilityTickable;
import alpvax.abilities.api.effect.EffectInstance;
import alpvax.abilities.api.effect.IAbilityEffect;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAbilityAffected extends ICapabilityTickable, INBTSerializable<NBTTagList>
{
	public Object getAffected();

	public void add(EffectInstance effect);

	public void remove(EffectInstance effect);

	public void removeAll(Predicate<EffectInstance> filter);

	public List<EffectInstance> getEffects();

	public boolean hasEffect(Predicate<IAbilityEffect> filter);

	@Override
	public void tick();
}
