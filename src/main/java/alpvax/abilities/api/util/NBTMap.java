package alpvax.abilities.api.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Throwables;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class NBTMap<T> implements INBTSerializable<NBTTagCompound>
{
	public static final String KEY_INSTANCE_CLASS = "InstanceClass";

	private final Class<T> superClass;
	private NBTTagCompound nbtCompound = new NBTTagCompound();

	public NBTMap(Class<T> rootClass)
	{
		superClass = rootClass;
	}

	@SuppressWarnings("unchecked")
	public final T get(String key)
	{
		NBTBase base = nbtCompound.getTag(key);
		T instance = null;
		if(base instanceof NBTTagCompound)
		{
			NBTTagCompound nbt = (NBTTagCompound)base;
			try
			{
				instance = newInstance(key, nbt);
			}
			catch(Exception e)
			{
				Throwables.propagate(e);
			}
			if(instance instanceof INBTSerializable)
			{
				((INBTSerializable<NBTTagCompound>)instance).deserializeNBT(nbt);
			}
		}
		if(instance == null)
		{
			instance = newBaseInstance(key, base);
		}
		return instance;
	}

	private final T newInstance(String nbtKey, NBTTagCompound nbt) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		Class<?>[] cTypes = getCtorTypes(nbtKey, nbt);
		Object[] cArgs = getCtorArgs(nbtKey, nbt);
		if(nbt.hasKey(KEY_INSTANCE_CLASS))
		{
			try
			{
				Class<?> clazz = Class.forName(nbt.getString(KEY_INSTANCE_CLASS));
				return superClass.cast(clazz.getConstructor(cTypes).newInstance(cArgs));
			}
			catch(ClassNotFoundException e)
			{
				Throwables.propagate(e);
			}
		}
		return superClass.getConstructor(cTypes).newInstance(cArgs);
	}

	protected abstract T newBaseInstance(String key, NBTBase base);

	protected abstract Class<?>[] getCtorTypes(String nbtKey, NBTTagCompound nbt);

	protected abstract Object[] getCtorArgs(String nbtKey, NBTTagCompound nbt);

	@SuppressWarnings("unchecked")
	public void add(String nbtKey, T item)
	{
		NBTBase nbt = new NBTTagByte((byte)1);//TRUE
		if(item instanceof INBTSerializable)
		{
			nbt = ((INBTSerializable<NBTTagCompound>)item).serializeNBT();
		}
		nbtCompound.setTag(nbtKey, nbt);
	}

	public void remove(String nbtKey)
	{
		nbtCompound.removeTag(nbtKey);
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		return nbtCompound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		nbtCompound = nbt;
	}

	public List<T> values()
	{
		List<T> list = new ArrayList<>();
		for(String key : nbtCompound.getKeySet())
		{
			T t = get(key);
			if(t != null)
			{
				list.add(t);
			}
		}
		return list;
	}
}
