package alpvax.abilities.api.skill;

import java.util.UUID;

public class SkillModifier
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
}
