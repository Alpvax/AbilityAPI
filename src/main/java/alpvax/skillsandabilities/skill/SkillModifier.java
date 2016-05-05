package alpvax.skillsandabilities.skill;

import java.util.UUID;

import alpvax.skillsandabilities.core.CharacterModConstants;
import alpvax.skillsandabilities.util.InstantiationHelper;
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

	public SkillModifier(UUID key, ModifierType operation)
	{
		id = key;
		type = operation;
	}

	public SkillModifier(UUID key, ModifierType operation, double amount)
	{
		this(key, operation);
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
			nbt.setString(CharacterModConstants.KEY_INSTANCE_CLASS, clazz.getName());
		}
		UUID id = getKey();
		nbt.setLong(CharacterModConstants.KEY_ID_MOST, id.getMostSignificantBits());
		nbt.setLong(CharacterModConstants.KEY_ID_LEAST, id.getLeastSignificantBits());
		nbt.setInteger(CharacterModConstants.KEY_MODIFIER_TYPE, getOperation().ordinal());
		nbt.setDouble(CharacterModConstants.KEY_MODIFIER_VALUE, getModifier());
		return nbt;
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		setModifier(nbt.getDouble(CharacterModConstants.KEY_MODIFIER_VALUE));
	}
	
	public static final SkillModifier loadFromNBT(NBTTagCompound nbt)
	{
		UUID id = new UUID(nbt.getLong(CharacterModConstants.KEY_ID_MOST), nbt.getLong(CharacterModConstants.KEY_ID_LEAST));
		ModifierType type = ModifierType.all_values[nbt.getInteger(CharacterModConstants.KEY_MODIFIER_TYPE)];
		SkillModifier sm = nbt.hasKey(CharacterModConstants.KEY_INSTANCE_CLASS) ? InstantiationHelper.newInstance(nbt.getString(CharacterModConstants.KEY_INSTANCE_CLASS), SkillModifier.class, id, type) : new SkillModifier(id, type);
		sm.deserializeNBT(nbt);
		return sm;
	}
}
