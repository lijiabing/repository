package com.sc.jysc.controller;

import com.sc.jysc.config.DefaultServiceException;
import com.sc.jysc.config.Result;
import com.sc.jysc.config.ResultGenerator;
import com.sc.jysc.entity.Sort;
import com.sc.jysc.sevice.SortEditService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sort")
@Api(value = "商品分类信息")
public class SortController {

    @Autowired
    private SortEditService sortEditService;

    @PostMapping("/addsort")
    public Result addSort(@RequestBody Sort sort) throws DefaultServiceException {
        return ResultGenerator.genSuccess(sortEditService.addSort(sort));
    }

    @GetMapping("/deletsort/{id}")
    public Result deleteSort(@PathVariable(required = true) String id){
        return ResultGenerator.genSuccess(sortEditService.deleteSort(id));
    }

    @GetMapping("/getsort/{id}")
    public Result get(@PathVariable(required = true) String id){
        return ResultGenerator.genSuccess(sortEditService.get(id));
    }
}
