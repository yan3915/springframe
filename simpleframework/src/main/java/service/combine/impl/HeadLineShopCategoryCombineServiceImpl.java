package service.combine.impl;

import entity.bo.HeadLine;
import entity.bo.ShopCategory;
import entity.dto.MainPageInfoDTO;
import entity.dto.Result;
import org.simpleframework.core.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import service.combine.HeadLineShopCategoryCombineService;
import service.solo.HeadLineService;
import service.solo.ShopCategoryService;

import java.util.List;
@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        //1.获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);//headLineSercice可用，相当于开关
        Result<List<HeadLine>> HeadLineResult = headLineService.queryHeadLine(headLineCondition, 1, 4);
        //2.获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult =shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);
        //3.合并两者并返回
        Result<MainPageInfoDTO> result = mergeMainPageInfoResult(HeadLineResult, shopCategoryResult);
        return result;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return  null;
    }
}
