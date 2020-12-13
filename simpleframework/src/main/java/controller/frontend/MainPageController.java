package controller.frontend;

import entity.bo.HeadLine;
import entity.dto.MainPageInfoDTO;
import entity.dto.Result;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Inject.Autowired;
import service.combine.HeadLineShopCategoryCombineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@Getter
public class MainPageController {
    @Autowired(value = "HeadLineShopCategoryCombineServiceImpl")
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;
    public Result<MainPageInfoDTO> getMainPageInfo (HttpServletRequest req, HttpServletResponse res){
       return  headLineShopCategoryCombineService.getMainPageInfo();
    }
}
