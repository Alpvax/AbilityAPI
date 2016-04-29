package alpvax.abilities.api.skill;

import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMilestoneState<T extends NBTBase> extends INBTSerializable<T>
{
	public SkillMilestone getMilestone();

	public boolean isAchieved();

	public void update(ISkillHandler handler);
}
