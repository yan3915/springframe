package org.simpleframework.core.annotation.Inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.BeanContainer;
import org.simpleframework.core.annotation.Util.ClassUtil;
import org.simpleframework.core.annotation.Util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

@Slf4j
public class DependencyInjector {
    //BEAN容器
    private BeanContainer beanContainer;
    public DependencyInjector(){
        beanContainer=BeanContainer.getInstance();
    }
    //执行IOC
    public void doIoc(){
        //1.遍历Bean容器中所有的Class对象
        if(ValidationUtil.isEmpty(beanContainer.getClasses())){
            log.warn("empty classset in BeanContainer");
            return;
        }
        for(Class<?> clazz : beanContainer.getClasses()){
            //2.遍历Class对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)){
                continue;
            }
            for(Field field : fields){
                //3.找出被Autowired标记的成员变量
                if(field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    //4、获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    //5.获取这些成员变量的类型在容器里对应的实例
                    Object fieldValue = getFieldInstance(fieldClass,autowiredValue);
                    //if(fieldValue == null){
                    //      throw new RuntimeException("unable to inject relevant type，target fieldClass is:" + fieldClass.getName()+"autowiredValue"+autowiredValue);
                   // } else {
                        //6.通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                        Object targetBean =  beanContainer.getBean(clazz);
                        ClassUtil.setField(field, targetBean, fieldValue, true);
                   // }
                }
            }
        }
    }
    /**
     * 根据Class在beanController里获取其实例或者实现类
     * */
    private Object getFieldInstance(Class<?> fieldClass,String autowiredValue){
       Object fieldValue= beanContainer.getBean(fieldClass);
       if(fieldValue!=null){
           return fieldValue;
       }else {
          Class<?> implementedClass=getImplementClass(fieldClass,autowiredValue);
          if(implementedClass!=null){
              return beanContainer.getBean(implementedClass);
          }else {
              return null;
          }
       }
    }
/**
 * 获取接口的实现类
 *
 * @return*/
    private Class<?> getImplementClass(Class<?> fieldClass,String autowiredValue) {
       Set<Class<?>> classSet=beanContainer.getClassbySuper(fieldClass);
       if(!ValidationUtil.isEmpty(classSet)){
            if(ValidationUtil.isEmpty(autowiredValue)) {
                if (classSet.size() == 1) {
                    return classSet.iterator().next();
                } else {
                    //只有用户才可以定义读出哪一个实现类，抛出异常
                    throw new RuntimeException("multiple implemented classes for" + fieldClass.getName() + "plase");
                }
            }else{
                for (Class<?> clazz:classSet){
                    if(autowiredValue.equals(clazz.getSimpleName())){
                       return  clazz;
                    }
                }
            }
       }
           return null;
    }
}

