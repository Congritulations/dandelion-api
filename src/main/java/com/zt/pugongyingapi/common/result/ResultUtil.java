package com.zt.pugongyingapi.common.result;

public class ResultUtil {


    /**
     * 成功并返回
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 成功并返回分页
     * @param object
     * @return
     */
    public static Result success(Object object,Long pageNo,Long pageSize,Integer totalNum) {
        PageResult result = new PageResult();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setTotalNum(totalNum);
        result.setData(object);
        return result;
    }

    /**
     * 成功不返回
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        return result;
    }

    /**
     * 成功返回空
     * @param object
     * @return
     */
    public static Result successAndNull(Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS_EMPTY_DATA.getCode());
        result.setMsg(ResultEnum.SUCCESS_EMPTY_DATA.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 返回失败
     * @param resultEnum
     * @return
     */
    public static Result fail(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    /**
     * 返回错误
     * @param resultEnum
     * @return
     */
    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

}
