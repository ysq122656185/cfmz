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
@Table(name = "chapter")
public class Chapter {
    @Id
    @ExcelIgnore
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "大小")
    private String size;
    @Excel(name = "时长")
    private String duration;
    @Excel(name = "封面", type = 2, needMerge = true)
    private String cover;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", format = "yyyy年MM月dd日")
    private Date c_create_date;
    @ExcelIgnore
    private String album_id;
}
