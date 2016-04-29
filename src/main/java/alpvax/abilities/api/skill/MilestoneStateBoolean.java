package alpvax.abilities.api.skill;

import net.minecraft.nbt.NBTTagByte;

public class MilestoneStateBoolean implements IMilestoneState<NBTTagByte>
{
	private boolean achieved = false;
	private final SkillMilestone milestone;

	public MilestoneStateBoolean(SkillMilestone milestone)
	{
		this.milestone = milestone;
	}

	@Override
	public SkillMilestone getMilestone()
	{
		return milestone;
	}

	@Override
	public boolean isAchieved()
	{
		return achieved;
	}

	@Override
	public void deserializeNBT(NBTTagByte nbt)
	{
		achieved = nbt.getByte() != 0;
	}

	@Override
	public NBTTagByte serializeNBT()
	{
		return new NBTTagByte((byte)(achieved ? 1 : 0));
	}

	@Override
	public void update(ISkillHandler handler)
	{
		achieved = milestone.hasAchieved(handler.getInstance(milestone.getSkill()));
	}
}
