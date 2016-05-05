package alpvax.skillsandabilities.util;

import java.lang.reflect.InvocationTargetException;

import com.google.common.base.Throwables;

public class InstantiationHelper
{
	public static <T> T newInstance(Class<T> clazz, Object... parameters)
	{
		Object[] paramTypes = new Object[parameters.length];
		for(int i = 0; i < parameters.length; i++)
		{
			paramTypes[i] = parameters[i].getClass();
		}
		return newInstance(clazz, paramTypes, parameters);
	}

	public static <T> T newInstance(String className, Class<T> superClass, Object... parameters)
	{
		Object[] paramTypes = new Object[parameters.length];
		for(int i = 0; i < parameters.length; i++)
		{
			paramTypes[i] = parameters[i].getClass();
		}
		return newInstance(className, superClass, paramTypes, parameters);
	}

	public static <T> T newInstance(String className, Class<T> superClass, Class<?>[] parameterTypes, Object[] parameters)
	{
		try
		{
			return superClass.cast(newInstance(Class.forName(className), parameterTypes, parameters));
		}
		catch(ClassNotFoundException e)
		{
			Throwables.propagate(e);
		}
		return newInstance(superClass, parameterTypes, parameters);
	}

	public static <T> T newInstance(Class<T> clazz, Class<?>[] parameterTypes, Object[] parameters)
	{
		try
		{
			return clazz.getConstructor(parameterTypes).newInstance(parameters);
		}
		catch(InstantiationException
				| IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException
				| NoSuchMethodException
				| SecurityException e)
		{
			Throwables.propagate(e);
		}
		return null;
	}
}
