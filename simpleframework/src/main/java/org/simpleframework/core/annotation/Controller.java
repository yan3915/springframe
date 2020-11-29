package org.simpleframework.core.annotation;


import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 此注解使用在类上面
 * */
@Target(ElementType.TYPE)
//设置注解的生命周期设置为runtime
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}
