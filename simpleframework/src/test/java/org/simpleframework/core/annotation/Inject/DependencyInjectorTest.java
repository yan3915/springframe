package org.simpleframework.core.annotation.Inject;

import controller.frontend.MainPageController;
import entity.dto.MainPageInfoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.annotation.BeanContainer;
import service.combine.impl.HeadLineShopCategoryCombineServiceImpl;
import service.combine.impl.HeadLineShopCategoryCombineServiceImpl2;

public class DependencyInjectorTest {
    @DisplayName("依赖注入")
    @Test
    public void doIocTest(){
       BeanContainer beanContainer= BeanContainer.getInstance();
       beanContainer.loadbeans("controller.frontend");
       Assertions.assertEquals(true,beanContainer.isLoaded());
      MainPageController mainPageController=(MainPageController) beanContainer.getBean(MainPageController.class);
      Assertions.assertEquals(true,mainPageController instanceof MainPageController);
      Assertions.assertEquals(null,mainPageController.getHeadLineShopCategoryCombineService());
      new DependencyInjector().doIoc();
      Assertions.assertEquals(null,mainPageController.getHeadLineShopCategoryCombineService());
      Assertions.assertEquals(false, mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl);
      Assertions.assertEquals(false, mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl2);

    }

}
