package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Charge;
import com.example.demo.entity.User;
import com.example.demo.mapper.ChargeMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/equ")
public class ChargeController {

    @Resource
    ChargeMapper chargeMapper;

    @PostMapping
    public Result<?> save(@RequestBody Charge charge){
        chargeMapper.insert(charge);
        return Result.success();

    }
    @DeleteMapping("/{id}")
    public Result update(@PathVariable Long id){
        id=id+1;
        chargeMapper.deleteById(id);
        return Result.success();
    }
    @PutMapping
    public Result update(@RequestBody Charge charge){
        chargeMapper.updateById(charge);
        return Result.success();
    }

    @GetMapping
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Charge> wrapper = Wrappers.<Charge>lambdaQuery();
        //如果为空也能查询出来
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Charge::getNickname,search);
        }
        Page<Charge> userPage=chargeMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);

    }
}
