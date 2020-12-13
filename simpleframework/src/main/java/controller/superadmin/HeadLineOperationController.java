package controller.superadmin;

import entity.bo.HeadLine;
import entity.dto.Result;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Inject.Autowired;
import service.solo.HeadLineService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
public class HeadLineOperationController {
    @Autowired
    private HeadLineService headLineService;
    public Result<Boolean> addHeadLine(HttpServletRequest req , HttpServletResponse resp ) {
         return headLineService.addHeadLine(new HeadLine());
    }

   /**
    *creat by yanzhe in 2020/11/23
    *  */
   //TODO:参数校验以及参数的请求参数的转化
    public Result<Boolean> removeHeadLine(int headLineId) {
        return headLineService.removeHeadLine(1);
    }

//TODO:参数校验以及参数的请求参数的转化
    public Result<Boolean> modifyHeadLine(HeadLine headLine) {
        return headLineService.modifyHeadLine(new HeadLine());
    }

    //TODO:参数校验以及参数的请求参数的转化
    public Result<HeadLine> queryHeadLineById(int headLineId) {

        return headLineService.queryHeadLineById(1);
    }
//TODO:参数校验以及参数的请求参数的转化

    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize) {
        return headLineService.queryHeadLine(null,1,100);
    }
}

