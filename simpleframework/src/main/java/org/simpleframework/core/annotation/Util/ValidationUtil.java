package org.simpleframework.core.annotation.Util;

import java.util.Collection;
import java.util.Map;

public class ValidationUtil {

    /**
     * String是否为null或""
     *
     * @param obj String
     * @return 是否为空
     */
    public static boolean isEmpty(String obj) {
        return (obj == null || "".equals(obj));
    }
    /**
     * 判断集合是否为空
     *
     * @param obj collectionb
     * @return 是否为空
     * */
    public static boolean isEmpty(Collection<?> obj){
        return obj==null||obj.isEmpty();
    }
    /**
     * Array是否为null或者size为0
     *
     * @param obj Array
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }
    /**
     * Map是否为null或size为0
     *
     * @param obj Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return obj == null || obj.isEmpty();
    }
}
