package alpvax.abilities.api.skill;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class MilestoneMap implements Iterable<IMilestoneState<?>>, INBTSerializable<NBTTagCompound>
{
	private final Skill skill;
	private Map<String, IMilestoneState<?>> milestoneStates = new HashMap<>();
	@SuppressWarnings("rawtypes")
	private Map<String, Class<? extends IMilestoneState>> milestoneStateClasses = new HashMap<>();

	public MilestoneMap(Skill skill)
	{
		this.skill = skill;
	}

	public void add(SkillMilestone milestone)
	{
		add(milestone.getKey(), milestone);
	}

	public void add(String key)
	{
		add(key, skill.getMilestone(key));
	}

	private <T extends IMilestoneState<?>> void add(String key, SkillMilestone milestone)
	{
		T state = milestone.newState();
		milestoneStates.put(key, state);
		milestoneStateClasses.put(key, state.getClass());
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		for(Map.Entry<String, IMilestoneState<?>> e : milestoneStates.entrySet())
		{
			nbt.setTag(e.getKey(), e.getValue().serializeNBT());
		}
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		for(String key : nbt.getKeySet())
		{
			if(!milestoneStates.containsKey(key))
			{
				add(skill.getMilestone(key));
			}
			get(key).deserializeNBT(nbt.getTag(key));
		}
	}

	@Override
	public Iterator<IMilestoneState<?>> iterator()
	{
		return milestoneStates.values().iterator();
	}

	@SuppressWarnings("unchecked")
	public <T extends IMilestoneState<U>, U extends NBTBase> T get(String key)
	{
		if(!milestoneStates.containsKey(key))
		{
			add(key);
		}
		return (T)milestoneStateClasses.get(key).cast(milestoneStates.get(key));
	}
}
