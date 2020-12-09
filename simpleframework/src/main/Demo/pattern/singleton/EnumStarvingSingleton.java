package demo.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EnumStarvingSingleton {

    //private  final Map<Class<?>,Object>ConcurrentHashMap beanMap=new ConcurrentHashMap();
   /
    private EnumStarvingSingleton(){

    }
    public static EnumStarvingSingleton getInstance(){
        return ContainerHolder.HOLDER.instance;
    }
    private enum ContainerHolder{
        HOLDER;
        private EnumStarvingSingleton instance;
        ContainerHolder(){
            instance = new EnumStarvingSingleton();
        }
    }

}
