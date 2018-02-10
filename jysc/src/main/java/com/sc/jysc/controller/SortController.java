package com.sc.jysc.controller;

import com.sc.jysc.config.DefaultServiceException;
import com.sc.jysc.config.Result;
import com.sc.jysc.config.ResultGenerator;
import com.sc.jysc.entity.Sort;
import com.sc.jysc.sevice.SortEditService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sort")
@Api(description = "商品分类信息")
public class SortController {

    @Autowired
    private SortEditService sortEditService;

    @ApiOperation(value = "新建商品分类")
    @PostMapping("/addsort")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message ="新建成功，data是一串字符串，失败则data为null或者抛出错误信息",response = Result.class)
    })
    public Result addSort(@RequestBody Sort sort) throws DefaultServiceException {
        return ResultGenerator.genSuccess(sortEditService.addSort(sort));
    }

    @ApiOperation(value = "删除商品分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "商品id",paramType = "path",dataType = "String"),
    })
    @GetMapping("/deletsort/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message ="删除成功，data是true，失败则data为false",response = Result.class)
    })
    public Result deleteSort(@PathVariable(required = true) String id) throws DefaultServiceException {
        return ResultGenerator.genSuccess(sortEditService.deleteSort(id));
    }

    @ApiOperation(value = "查询单个商品分类")
    @GetMapping("/getsort/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "商品id",paramType = "path",dataType = "String"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message ="查询成功，data是一个封装了商品信息的实体，失败则null或者抛出错误信息，message是错误信息",response = Result.class)
    })
    public Result get(@PathVariable(required = true) String id) throws DefaultServiceException {
        return ResultGenerator.genSuccess(sortEditService.get(id));
    }
}
