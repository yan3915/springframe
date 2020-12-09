package org.simpleframework.core.annotation.Inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.BeanContainer;
import org.simpleframework.core.annotation.Util.ValidationUtil;

import java.io.File;
import java.lang.reflect.Field;

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
        for (Class<?> clazz: beanContainer.getClasses()){
            //2.遍历Class对象的所有成员变量
            Field[] fields=clazz.getDeclaredFields();
            //3.找出被Autowired标记的成员变量
            //4获取这些成员变量的类型
            //5.获取这些成员变量的类型在容器里对应的实例
            //6.通过反射将对应的成员变量实例注入到成员变量所在类的实例里
        }





    }
}
