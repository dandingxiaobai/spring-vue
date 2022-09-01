package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("charge")//对应数据库名
@Data //自动生成getset方法
public class Charge {

    @TableId(type = IdType.AUTO)//定义自增
    private Integer id;
    private String num;
    private String nickname;
    private String ip;
    private String host;
    private String type;
    private String time;
    private String peo;
}
