package controller.frontend;

import entity.bo.HeadLine;
import entity.dto.MainPageInfoDTO;
import entity.dto.Result;
import org.simpleframework.core.annotation.Controller;
import service.combine.HeadLineShopCategoryCombineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class MainPageController {
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;
    public Result<MainPageInfoDTO> getMainPageInfo (HttpServletRequest req, HttpServletResponse res){
       return  headLineShopCategoryCombineService.getMainPageInfo();
    }
}
