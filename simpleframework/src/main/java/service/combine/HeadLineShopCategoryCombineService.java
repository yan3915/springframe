package service.combine;


import entity.dto.MainPageInfoDTO;
import entity.dto.Result;

public interface HeadLineShopCategoryCombineService {
    Result<MainPageInfoDTO> getMainPageInfo();
}
