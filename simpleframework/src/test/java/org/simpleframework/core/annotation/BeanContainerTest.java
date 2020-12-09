package org.simpleframework.core.annotation;

import controller.DispatcherServlet;
import controller.frontend.MainPageController;
import org.junit.jupiter.api.*;
import service.solo.HeadLineService;
import service.solo.impl.HeadLineServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//决定测试类的执行顺序
public class BeanContainerTest {
    private static  BeanContainer beanContainer;
    @BeforeAll
    static void init(){

      beanContainer= BeanContainer.getInstance();
    }
    @DisplayName("加载目标类及其实例到BeanContainer:loadBeansTest")
    @Order(1)//第一个执行单元测试
    @Test
    /**
     * controller和service包中的所有的beans
     * */
    public void loadBeansTest(){
        Assertions.assertEquals(false,beanContainer.isLoaded());
        beanContainer.loadbeans("controller");
        Assertions.assertEquals(3,beanContainer.size());
        Assertions.assertEquals(true,beanContainer.isLoaded());


    }
    @DisplayName("根据类获取其实例：getBeanTest")
    @Order(2)
    @Test
    public  void getBeanTest(){
        MainPageController controller=(MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true,controller instanceof MainPageController);//panduan 收集到的实例是不是MainpageCOntroller创建出来的实例
        DispatcherServlet dispatcherServlet=(DispatcherServlet) beanContainer.getBean(DispatcherServlet.class);
        Assertions.assertEquals(null,dispatcherServlet);
    }
    @DisplayName("根据注解获取对应的实例;getClassByAnnotataionTest")
    @Order(3)
    @Test
    public void getClassByAnnotataionTest(){
        Assertions.assertEquals(true,beanContainer.isLoaded());
        Assertions.assertEquals(3,beanContainer.getClassbyAnnotation(Controller.class).size());
    }

    /**
    @DisplayName("根据接口获取实现类，：getClassBySuperTest")
    @Order(4)
    @Test
    public void getClassesBySuperTest(){
        Assertions.assertEquals(true,beanContainer.isLoaded());

        Assertions.assertEquals(true,beanContainer.getClassbySuper(HeadLineService.class) .contains(HeadLineServiceImpl.class));

        //Assertions.assertEquals(true,beanContainer.getClassbySuper(HeadLineServiceImpl.class));
    }

    */
}
