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
@Table(name = "Lbimg")
public class Lbimg {
    @Id
    @ExcelIgnore
    private String l_id;
    @Excel(name = "名称")
    private String l_name;
    @Excel(name = "封面", type = 2, width = 40, height = 30)
    private String l_cover;
    @Excel(name = "描述")
    private String l_describe;
    @Excel(name = "状态")
    private String l_status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", format = "yyyy年MM月dd日")
    private Date l_create_date;
}
