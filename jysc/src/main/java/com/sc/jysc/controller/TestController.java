package com.sc.jysc.controller;

import com.sc.jysc.config.Result;
import com.sc.jysc.config.ResultGenerator;
import com.sc.jysc.entity.BasicUser;
import com.sc.jysc.sevice.DefaultUserService;
import com.sc.jysc.util.CurrentPage;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Jackbing on 2017/12/26.
 */
@Api(description = "测试")
@RestController
@RequestMapping("/test/")
public class TestController {


    @Autowired
    private DefaultUserService userService;
    @GetMapping("/user/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页数",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页数量",paramType = "query",dataType = "Integer",required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "返回用户列表",response = BasicUser.class)
    })
    public Result<CurrentPage<BasicUser>> getUsers(@RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize){
        return ResultGenerator.genSuccess(userService.getUsers(pageNum,pageSize));
    }
}
