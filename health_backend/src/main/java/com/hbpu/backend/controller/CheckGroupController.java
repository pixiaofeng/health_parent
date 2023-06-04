package com.hbpu.backend.controller;

import com.hbpu.backend.vo.CheckGroupParam;
import com.hbpu.common.constant.MessageConstant;
import com.hbpu.common.entity.PageResult;
import com.hbpu.common.entity.QueryPageBean;
import com.hbpu.common.entity.Result;
import com.hbpu.common.pojo.CheckGroup;
import com.hbpu.intetface.service.CheckGroupService;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b><code>CheckGroupController</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2023/6/4 17:15.
 *
 * @author 皮
 */
@RestController
@RequestMapping("/checkgroup")
@Api(tags ="检查组控制器类")
public class CheckGroupController {

    @DubboReference
    private CheckGroupService checkGroupService;

    //添加检查组
    @PostMapping("/add")
    public Result addCheckGroup(@RequestBody CheckGroupParam checkGroupParam){
        try {
            checkGroupService.addCheckGroup(checkGroupParam.getCheckGroup(),checkGroupParam.getCheckItemIds());
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
       return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }


    //分页查询
    @PostMapping("/findByPage")
    public PageResult findByPage(@RequestBody QueryPageBean queryPageBean){
            PageResult byPage = checkGroupService.findByPage(queryPageBean);
            return byPage;
    }

    //根据检查组id查询检查组对象
    @GetMapping("/findById/{groupId}")
    public Result findById(@PathVariable Integer groupId){
        CheckGroup checkGroup=null;
        try {
             checkGroup = checkGroupService.selectById(groupId);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
       return new Result(true,MessageConstant.QUERY_CHECKGROUP_FAIL,checkGroup);
    }

    //查询检查组对应的检查项id，用于回显
    @GetMapping("/findItemIds/{groupId}")
    public Result findItemIds(@PathVariable Integer groupId){
        List<Integer> list=null;
        try {
            list = checkGroupService.selectItemIds(groupId);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody CheckGroupParam checkGroupParam){
        try {
            checkGroupService.edit(checkGroupParam.getCheckGroup(),checkGroupParam.getCheckItemIds());
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }
}
