package service.solo;

/**
 * create by yanzhe
 * 连接头条的操作
 * */

import entity.bo.HeadLine;
import entity.dto.Result;

import java.util.List;
/**
 *
 * 增删查改
 * */
public interface HeadLineService {
    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(int headLineId);
    Result<Boolean> modifyHeadLine(HeadLine headLine);
    Result<HeadLine> queryHeadLineById(int headLineId);
    Result<List<HeadLine>>queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);
}
