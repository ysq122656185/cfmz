package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "c_user")
public class User {
    @Id
    @ExcelIgnore
    private String id;
    @Excel(name = "用户名")
    private String username;
    @ExcelIgnore
    private String password;
    @ExcelIgnore
    private String salt;
    @Excel(name = "法号")
    private String dharma;
    @Excel(name = "省份")
    private String province;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "头像", type = 2, width = 40, height = 30)
    private String photo;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "电话")
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "注册时间", format = "yyyy年MM月dd日")
    private Date u_create_date;
    @Excel(name = "上师名称")
    private String g_name;
}
