package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album")
public class Album {
    @Id
    @ExcelIgnore
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "封面", type = 2, width = 40, height = 30, needMerge = true)
    private String cover;
    @Excel(name = "作者", needMerge = true)
    private String author;
    @Excel(name = "播音", needMerge = true)
    private String beam;
    @Excel(name = "评分", needMerge = true)
    private Integer score;
    @Excel(name = "章节数量", needMerge = true)
    private Integer count;
    @Excel(name = "介绍", needMerge = true)
    private String intro;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", format = "yyyy年MM月dd日", needMerge = true)
    private Date a_create_date;
    @ExcelCollection(name = "本专辑下章节")
    private List<Chapter> chapters;
}
