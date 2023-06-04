package com.hbpu.backend.controller;

import com.hbpu.common.constant.MessageConstant;
import com.hbpu.common.entity.PageResult;
import com.hbpu.common.entity.QueryPageBean;
import com.hbpu.common.entity.Result;
import com.hbpu.common.pojo.CheckItem;
import com.hbpu.intetface.service.CheckItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

/**
 * <b><code>CheckItemController</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2023/5/27 21:47.
 *
 * @author 皮
 */
@RestController //将该类交给ioc容器管理，并类中的控制器方法返回值作为响应体
@RequestMapping("/checkitem")
@Api(tags = "检查项控制管理器") //用来在Knife4接口文档中设置该controller类名
public class CheckItemController {

    @DubboReference
    //使用该注解通过duboo，引用zookeeper注册中心中的服务，并将代理对象赋值给引用
    private CheckItemService checkItemService;


    @PostMapping("/add")
    //添加体检项，将前端以ajax且json格式发送过来的参数封装为CheckItem对象
    @ApiOperation("添加检查项") //设置在Knife4接口文档中该接口的名字
    public Result addCheckItem(@RequestBody @ApiParam(name = "检查项对象",value = "checkItem") CheckItem checkItem){
        Result result=null;
        try {
            checkItemService.insert(checkItem);
            //成功
            result=new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //失败
            result=new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return  result;
    }

    @PostMapping("/selectByPage")
    //分页查询检查项数据
    @ApiOperation("分页查询检查项") //设置在Knife4接口文档中该接口的名字
    public PageResult selectByPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.selectByPage(queryPageBean);
        return pageResult;
    }

    @DeleteMapping("/{id}")
    //删除
    @ApiOperation("删除检查项") //设置在Knife4接口文档中该接口的名字
    public Result deleteById(@PathVariable Integer id){
        try {
            checkItemService.deleteById(id);
        }catch (RuntimeException e){
            e.printStackTrace();
            Result result=new Result(false,e.getMessage());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            Result result=new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
            return result;
        }
        Result result=new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
        return  result;
    }

    //根据id查询检查项用于回显
    @GetMapping("/{id}")
    @ApiOperation("根据id查询检查项")
    public Result selectById(@PathVariable Integer id){
        CheckItem checkItem=null;
        try {
             checkItem = checkItemService.selectById(id);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
       return  new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);

    }


    //根据id修改检查项
    @PutMapping("/update")
    @ApiOperation("根据id修改检查项")
    public Result updateById(@RequestBody CheckItem checkItem){
        try {
            checkItemService.updateById(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);

    }

    //查询所有检查项，用于在添加检查项组时，选择检查项
    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckItem> checkItems=null;
        try {
            checkItems = checkItemService.selectAll();
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
       return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItems);
    }

}
