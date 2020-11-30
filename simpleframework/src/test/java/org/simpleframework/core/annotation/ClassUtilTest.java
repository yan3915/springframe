package org.simpleframework.core.annotation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.annotation.Util.ClassUtil;

import java.util.Set;

public class ClassUtilTest {
    @DisplayName("提取目标类方法:extractPackageClassTest")
    @Test
    public void extractPackageClassTest(){
       Set<Class<?>>  classSet =ClassUtil.extractPackageClass("entity");
       System.out.println(classSet);
        Assertions.assertEquals(4,classSet.size());
    }

}
