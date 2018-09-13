package com.zm.utils.javautil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.hw.domain.BaseDomain;

/**
 * 反射的Utils函数集合. 提供访问私有变量,获取泛型类型Class,提取集合中元素的属性等Utils函数.
 * 
 * @author hcy
 */
public class ReflectionUtils {

	private static Logger logger = LoggerFactory
			.getLogger(ReflectionUtils.class);

	private ReflectionUtils() {
	}

	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 */
	public static Object getFieldValue(final Object object,
			final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null)
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(final Object object,
			final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null)
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 */
	protected static Field getDeclaredField(final Object object,
			final String fieldName) {
		Assert.notNull(object, "object不能为空");
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	@SuppressWarnings("unchecked")
	protected static Field getDeclaredField(final Class clazz,
			final String fieldName) {
		Assert.notNull(clazz, "clazz不能为空");
		Assert.hasText(fieldName, "fieldName");
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 强制转换fileld可访问.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers())
				|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends HibernateDao<User>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends
	 * HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be
	 *         determined
	 */

	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz,
			final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of "
					+ clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName()
					+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * 提取集合中的对象的属性,组合成List.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertityName
	 *            要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List fetchElementPropertyToList(final Collection collection,
			final String propertyName) throws Exception {

		List list = new ArrayList();

		for (Object obj : collection) {
			list.add(PropertyUtils.getProperty(obj, propertyName));
		}

		return list;
	}

	/**
	 * 提取集合中的对象的属性,组合成由分割符分隔的字符串.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertityName
	 *            要提取的属性名.
	 * @param separator
	 *            分隔符.
	 */
	@SuppressWarnings("unchecked")
	public static String fetchElementPropertyToString(
			final Collection collection, final String propertyName,
			final String separator) throws Exception {
		List list = fetchElementPropertyToList(collection, propertyName);
		return StringUtils.join(list.toArray(), separator);
	}

	/**
	 * 利用递归找一个类的指定方法，如果找不到，去父亲里面找直到最上层Object对象为止。
	 * 
	 * @param clazz
	 *            目标类
	 * @param methodName
	 *            方法名
	 * @param classes
	 *            方法参数类型数组
	 * @return 方法对象
	 * @throws Exception
	 */
	public static Method getMethod(Class clazz, String methodName,
			final Class[] classes) throws Exception {
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(methodName, classes);
		} catch (NoSuchMethodException e) {
			try {
				method = clazz.getMethod(methodName, classes);
			} catch (NoSuchMethodException ex) {
				if (clazz.getSuperclass() == null) {
					return method;
				} else {
					method = getMethod(clazz.getSuperclass(), methodName,
							classes);
				}
			}
		}
		return method;
	}

	/**
	 * 
	 * @param obj
	 *            调整方法的对象
	 * @param methodName
	 *            方法名
	 * @param classes
	 *            参数类型数组
	 * @param objects
	 *            参数数组
	 * @return 方法的返回值
	 */
	public static Object invoke(final Object obj, final String methodName,
			final Class[] classes, final Object[] objects) {
		try {
			Method method = getMethod(obj.getClass(), methodName, classes);
			method.setAccessible(true);// 调用private方法的关键一句话
			return method.invoke(obj, objects);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object invoke(final Object obj, final String methodName,
			final Class[] classes) {
		return invoke(obj, methodName, classes, new Object[] {});
	}

	public static Object invoke(final Object obj, final String methodName) {
		return invoke(obj, methodName, new Class[] {}, new Object[] {});
	}

	/**
	 * 获取所有属性名称和值
	 * @param object
	 * @throws Exception
	 */
	public static Map getObjectValue(Object object) throws Exception {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		// 我们项目的所有实体类都继承BaseDomain （所有实体基类：该类只是串行化一下）
		// 不需要的自己去掉即可
		if (object != null && object instanceof BaseDomain) {// if (object!=null
																// ) ----begin
			// 拿到该类
			Class<?> clz = object.getClass();
			// 获取实体类的所有属性，返回Field数组
			Field[] fields = clz.getDeclaredFields();

			for (Field field : fields) {// --for() begin
				//System.out.println(field.getGenericType());// 打印该类的所有属性类型

				// 如果类型是String
				if (field.getGenericType().toString()
						.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
					// 拿到该属性的gettet方法
					/**
					 * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
					 * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
					 * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
					 */
					Method m = (Method) object.getClass().getMethod(
							"get" + getMethodName(field.getName()));

					String val = (String) m.invoke(object);// 调用getter方法获取属性值
					//if (val != null) {
						returnMap.put(field.getName(), val);
					//}

				}

				// 如果类型是Integer
				if (field.getGenericType().toString()
						.equals("class java.lang.Integer")) {
					Method m = (Method) object.getClass().getMethod(
							"get" + getMethodName(field.getName()));
					Integer val = (Integer) m.invoke(object);
					//if (val != null) {
						returnMap.put(field.getName(), val);
					//}

				}

				// 如果类型是Double
				if (field.getGenericType().toString()
						.equals("class java.lang.Double")) {
					Method m = (Method) object.getClass().getMethod(
							"get" + getMethodName(field.getName()));
					Double val = (Double) m.invoke(object);
					//if (val != null) {
						returnMap.put(field.getName(), val);
					//}

				}

				// 如果类型是Boolean 是封装类
				if (field.getGenericType().toString()
						.equals("class java.lang.Boolean")) {
					Method m = (Method) object.getClass().getMethod(
							field.getName());
					Boolean val = (Boolean) m.invoke(object);
					//if (val != null) {
						returnMap.put(field.getName(), val);
					//}

				}

				// 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的
				// 反射找不到getter的具体名
				if (field.getGenericType().toString().equals("boolean")) {
					Method m = (Method) object.getClass().getMethod(
							field.getName());
					Boolean val = (Boolean) m.invoke(object);
					//if (val != null) {
						returnMap.put(field.getName(), val);
					//}

				}
				// 如果类型是Date
				if (field.getGenericType().toString()
						.equals("class java.util.Date")) {
					Method m = (Method) object.getClass().getMethod(
							"get" + getMethodName(field.getName()));
					Date val = (Date) m.invoke(object);
					//if (val != null) {
						returnMap.put(field.getName(), val);
					//}

				}
				// 如果类型是Short
				if (field.getGenericType().toString()
						.equals("class java.lang.Short")) {
					Method m = (Method) object.getClass().getMethod(
							"get" + getMethodName(field.getName()));
					Short val = (Short) m.invoke(object);
					//if (val != null) {
						returnMap.put(field.getName(), val);
					//}

				}
				// 如果还需要其他的类型请自己做扩展

			}// for() --end

		}// if (object!=null ) ----end
		return returnMap;
	}

	// 把一个字符串的第一个字母大写、效率是最高的、
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

}