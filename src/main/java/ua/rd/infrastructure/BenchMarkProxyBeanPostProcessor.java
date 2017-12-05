package ua.rd.infrastructure;

import java.lang.reflect.Method;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BenchMarkProxyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {   
        
        Class<?> clazz = bean.getClass();
        Object beanProxy = null;
        
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            BenchMark benchmark = method.getAnnotation(BenchMark.class);
            if (benchmark != null && benchmark.enabled()) {
                beanProxy = ProxyCreator.newInstance(bean, clazz); 
            }
        }
        return beanProxy == null ? bean : beanProxy; 
    }
}
