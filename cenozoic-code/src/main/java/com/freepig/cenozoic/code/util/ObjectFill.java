package com.freepig.cenozoic.code.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.freepig.cenozoic.code.entity.dbmapper.User;

public class ObjectFill {

	private final static Map<Class<?>, Map<String, Field>> FIELDS = new ConcurrentHashMap<Class<?>, Map<String, Field>>();

	public void scanClass(Class<?> c) {
		FIELDS.put(c, fieldsConvertMap(c.getDeclaredFields()));
	}

	public Map<String, Field> fieldsConvertMap(Field... o) {
		Map<String, Field> map = new HashMap<String, Field>();
		for (Field f : o) {
			f.setAccessible(true);
			map.put(f.getName(), f);
		}
		return map;
	}

	public final <T> T fillObject(Class<T> t, Map<String, String> data) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		return fillObject(t.newInstance(), data);
	}

	public final <T> T fillObject(T t, Map<String, String> data) throws IllegalArgumentException, IllegalAccessException {
		System.out.println(t.getClass());
		Class<?> c = t.getClass();
		if (!FIELDS.containsKey(c))
			scanClass(c);
		Map<String, Field> fields = FIELDS.get(c);
		String value;
		Field field;
		for (Entry<String, String> en : data.entrySet()) {
			value = en.getValue();
			if (null == value || value.isEmpty())
				continue;
			field = fields.get(en.getKey());
			field.set(t, getValue(field.getType(), value));
		}

		return t;
	}

	private Object getValue(Class<?> c, String data) {
		String simpleName = c.getSimpleName();
		if (c.isArray()) {
			System.err.println("数组末实现");
			return null;
		}
		else if (simpleName.equalsIgnoreCase("String"))
			return data;
		else if (simpleName.equalsIgnoreCase("boolean") || simpleName.equalsIgnoreCase("Boolean"))
			return Boolean.valueOf(data);
		else if (simpleName.equalsIgnoreCase("int") || simpleName.equalsIgnoreCase("Integer"))
			return Integer.valueOf(data);
		else if (simpleName.equalsIgnoreCase("byte"))
			return Byte.valueOf(data);
		else if (simpleName.equalsIgnoreCase("char") || simpleName.equalsIgnoreCase("Character"))
			return Character.valueOf(data.charAt(0));
		else if (simpleName.equalsIgnoreCase("double"))
			return Double.valueOf(data);
		else if (simpleName.equalsIgnoreCase("long"))
			return Long.valueOf(data);
		else if (simpleName.equalsIgnoreCase("short"))
			return Short.valueOf(data);
		else if (simpleName.equalsIgnoreCase("float"))
			return Float.valueOf(data);
		return data;
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		System.out.println(AbstractHibernateUtil.class);
		ObjectFill of = new ObjectFill();
		Map<String, String> a = new HashMap<String, String>();
		a.put("name", "李好吗");
		a.put("phone", "131239919191");
		User u = of.fillObject(User.class, a);
		System.out.println(u.getName());
	}

}
