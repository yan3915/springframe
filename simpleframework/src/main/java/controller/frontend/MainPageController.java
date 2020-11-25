package controller.frontend;

import entity.bo.HeadLine;
import entity.dto.MainPageInfoDTO;
import entity.dto.Result;
import service.combine.HeadLineShopCategoryCombineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageController {
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;
    public Result<MainPageInfoDTO> getMainPageInfo (HttpServletRequest req, HttpServletResponse res){
       return  headLineShopCategoryCombineService.getMainPageInfo();
    }
}
