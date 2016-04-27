package alpvax.abilities.api.skill;

import java.util.UUID;

import com.google.common.base.Throwables;

import alpvax.abilities.core.AbilitiesAPIConstants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class SkillModifier implements INBTSerializable<NBTTagCompound>
{
	/**
	 * The operation of the modifier, applied in the given order.
	 */
	public static enum ModifierType
	{
		/** Adds directly to the base value */
		ADDITION_BASE,
		/** Modified base value is multiplied by the sum of these modifiers */
		MULTIPLICATIVE_SUM,
		/** Modified base value is multiplied by the product of these modifiers */
		MULTIPLICATIVE_PRODUCT,
		/** Adds directly to the modified value */
		ADDITION_FINAL;

		public static ModifierType[] all_values = values();
	}

	private final ModifierType type;
	private final UUID id;
	private double modifier;

	public SkillModifier(UUID key, ModifierType operation, double amount)
	{
		id = key;
		type = operation;
		setModifier(amount);
	}

	public ModifierType getOperation()
	{
		return type;
	}

	public UUID getKey()
	{
		return id;
	}

	public double getModifier()
	{
		return modifier;
	}

	public void setModifier(double amount)
	{
		modifier = amount;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		Class<? extends SkillModifier> clazz = getClass();
		if(clazz != SkillModifier.class)
		{
			nbt.setString(AbilitiesAPIConstants.KEY_INSTANCE_CLASS, clazz.getName());
		}
		UUID id = getKey();
		nbt.setLong(AbilitiesAPIConstants.KEY_ID_MOST, id.getMostSignificantBits());
		nbt.setLong(AbilitiesAPIConstants.KEY_ID_LEAST, id.getLeastSignificantBits());
		nbt.setInteger(AbilitiesAPIConstants.KEY_MODIFIER_TYPE, getOperation().ordinal());
		nbt.setDouble(AbilitiesAPIConstants.KEY_MODIFIER_VALUE, getModifier());
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		setModifier(nbt.getDouble(AbilitiesAPIConstants.KEY_MODIFIER_VALUE));
	}

	public static final SkillModifier loadFromNBT(NBTTagCompound nbt)
	{
		SkillModifier sm = getNewInstance(nbt);
		sm.deserializeNBT(nbt);
		return sm;
	}

	private static final SkillModifier getNewInstance(NBTTagCompound nbt)
	{
		UUID id = new UUID(nbt.getLong(AbilitiesAPIConstants.KEY_ID_MOST), nbt.getLong(AbilitiesAPIConstants.KEY_ID_LEAST));
		ModifierType type = ModifierType.all_values[nbt.getInteger(AbilitiesAPIConstants.KEY_MODIFIER_TYPE)];
		if(nbt.hasKey(AbilitiesAPIConstants.KEY_INSTANCE_CLASS))
		{
			String cname = nbt.getString(AbilitiesAPIConstants.KEY_INSTANCE_CLASS);
			try
			{
				Class<?> clazz = Class.forName(cname);
				return SkillModifier.class.cast(clazz.getConstructor(UUID.class, ModifierType.class, double.class).newInstance(id, type, 0D));
			}
			catch(Exception e)
			{
				Throwables.propagate(e);
			}
		}
		return new SkillModifier(id, type, 0D);
	}
}
