package ua.rd.infrastructure;

import java.lang.reflect.*;
import java.lang.reflect.Method;

import org.springframework.util.ClassUtils;

import ua.rd.infrastructure.BenchMark;

public class ProxyCreator implements InvocationHandler {

	private Object obj;
	private Class<?> clazz;

	public static Object newInstance(Object obj, Class<?> clazz) {
		return java.lang.reflect.Proxy.newProxyInstance(
				obj.getClass().getClassLoader(),
				getAllInterfaces(obj),
				new ProxyCreator(obj, clazz));
	}



    private static Class<?>[] getAllInterfaces(Object obj) {
        return ClassUtils.getAllInterfaces(obj);       
    }
	
	

	private ProxyCreator(Object obj, Class<?> clazz) {
		this.obj = obj;
		this.clazz = clazz;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {

		Class<?>[] inpArgTypes = getArgTypes(args);
		Method method = clazz.getMethod(m.getName(), inpArgTypes);

		BenchMark benchmark = method.getAnnotation(BenchMark.class);
		if (benchmark != null && benchmark.enabled()) {
			return countTimeExecutionAndInvokeMethod(m, args);
		}
		return m.invoke(obj, args);
	}

	private Class<?>[] getArgTypes(Object[] args) {
		Class<?>[] argsTypes = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			argsTypes[i] = args[i].getClass();
		}
		return argsTypes;
	}

	private Object countTimeExecutionAndInvokeMethod(Method m, Object[] args)
			throws IllegalAccessException, InvocationTargetException {
		long startTime = System.nanoTime();
		Object result = m.invoke(obj, args);
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Method: " + m.getName() + " " + elapsedTime + "[ns]");
		return result;
	}
}
