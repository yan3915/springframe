package org.simpleframework.core.annotation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)//无参构造
/**
 * 获取bean的实例
 *
 * */
public class BeanContainer {

    private final Map<Class<?>,Object> beanMap=new ConcurrentHashMap();
    /**
     * 配置的管理于获取
     * 获取指定范围内的cLASS对象
     * 依据配置提取Class对象，连同实例一并存入容器
     * */
    /**
     * 注解列表
     * */

    private static  final List<Class<?extends Annotation>> BEAN_ANNOTATION= Arrays.asList(Component.class,Controller.class,Service.class,Repository.class);
    /***2020.12.1，晚安打工人**/
    public  static BeanContainer getInstance(){
        return ContainerHolder.HOLDER.instance;
    }
    private enum ContainerHolder{
        HOLDER;
        private BeanContainer instance;
        ContainerHolder(){
            instance=new BeanContainer();
        }

    }
}
