package org.simpleframework.core.annotation;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Util.ClassUtil;
import org.simpleframework.core.annotation.Util.ValidationUtil;
import org.springframework.beans.propertyeditors.ClassArrayEditor;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)//无参构造器
/**
 * 获取bean的实例，
 * create by yanzhe
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
     *
     *  */
    private static  final List<Class<?extends Annotation>> BEAN_ANNOTATION= Arrays.asList(Component.class,Controller.class,Service.class,Repository.class);
    /***2020.12.1，晚安打工人**/
    public  static BeanContainer getInstance()//获取单例容器中的单例
    {
        return ContainerHolder.HOLDER.instance;
    }
    private enum ContainerHolder{
        HOLDER;
        private BeanContainer instance;
        ContainerHolder()//私有的枚举类型，所以可以不再使用私有的符号

        {
            instance=new BeanContainer();//构造了单例
        }

    }
    /**
     * 判断容器是否加载过bean
     *
     * */
    private  boolean loaded=false;
    /**
     * 是否加载过bean
     * @return 是否加载
     * */
    public   boolean isLoaded(){
        return loaded;
    }
    /**
     * Bean 实例的数量
     *
     * */
    public int size(){
        return beanMap.size();
    }
    /**
     * 扫描加载所有的bean
     * @param packageName
     * */
     public synchronized void loadbeans(String packageName){
         //判断容器是否加载bean
         if(isLoaded()){
             log.warn("BeanContainer has been loaded");
             return;
         }
         Set<Class<?>>classSet= ClassUtil.extractPackageClass(packageName);
         if(ValidationUtil.isEmpty(classSet)){
             log.warn("exract nothing from packageName"+packageName);
             return;
         }
         for(Class<?> clazz:classSet){
             for (Class<?extends Annotation>annotion :BEAN_ANNOTATION){
                 if(clazz.isAnnotationPresent(annotion)){
                     beanMap.put(clazz,ClassUtil.newInstance(clazz,true));

                 }
             }
         }
         loaded=true;
         //存在问题，多个线程同时加载时的冲突，对此使用线程锁synchronized

     }
    /**
     * 添加一个class对象及其Bean实例
     *
     * @param clazz Class对象
     * @param bean Beans实例
     * @return 所有的Bean实例，没有的返回null
     *
     */
     public  Object addBean(Class<?>clazz,Object bean){
          return beanMap.put(clazz,bean);
     }
     /**
      * 移除一个IOC容器管理的对象
      * @param clazz Class对象
      * @return  删除bean的实例，没有的返回null
      * */
     public Object removeBean(Class<?> clazz){
         return beanMap.remove(clazz);
     }
     /**
      * 根据Class 对象获取Bean实例
      * @param clazz class对象
      * @return Bean实例
      * */
    public Object getBean(Class<?> clazz){
         return beanMap.get(clazz);
     }
     /**
      * 获取对外所有的键位集合
      * */
    public Set<Class<?>> getClasses(){
         return beanMap.keySet();
     }
     /**
      * 获取所有的beans的集合
      * */
     public Set<Object> getBeans(){
         return new HashSet<>(beanMap.values());
    }
    /**
     * 根据注解选出Class集合
     * */
   public Set<Class<?>> getClassbyAnnotation(Class<? extends Annotation> annotation){
       //获取beanMapde 所有对象
       Set<Class<?>> keySet=getClasses();
       if(ValidationUtil.isEmpty(keySet)){
           log.warn("nothing in beanMap");
           return null;
       }
       //通过注解选出被标识的对象，并添加到集合里
       Set<Class<?>> classSet=new HashSet<>();
       for(Class<?> clazz:keySet){
           //类是否有相关的注解呢
           if(clazz.isAnnotationPresent(annotation)){
              classSet.add(clazz);
           }
       }
        return classSet.size()>0?classSet:null;
    }
    /**
     * 通过接口或者父类获取实现类或者子类的Class集合，不包括其本身
     * @param interfaceOrClass 接口Class或者父类Class
     * */
    public Set<Class<?>> getClassbySuper(Class<?> interfaceOrClass  ){
        //获取beanMapde 所有对象
        Set<Class<?>> keySet=getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //判断keyset里的元素是否是传入接口或者类的父类，如果是，将其添加到Classset
        Set<Class<?>> classSet=new HashSet<>();
        for(Class<?> clazz:keySet){
            //判断keySet里的元素是否是传入的接口或者类的子类
            if(interfaceOrClass.isAssignableFrom(clazz)&&!clazz.equals(interfaceOrClass)){
                classSet.add(clazz);
            }
        }
        return classSet.size()>0?classSet:null;
    }
}
