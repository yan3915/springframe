package demo.pattern.singleton;

import java.lang.reflect.InvocationTargetException;
/**
 * 这是一个单例模式的例子
 *
 * */
public class StarvingSingleton {
    private static final StarvingSingleton starvingSingleton = new StarvingSingleton();//防止外界调用

    private StarvingSingleton() {
    }

    public static StarvingSingleton getInstance() {
        return starvingSingleton;
    }

    public static void main(String[] args) {
        System.out.println(StarvingSingleton.getInstance());
        System.out.println(StarvingSingleton.getInstance());

    }

}