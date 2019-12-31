package com.baizhi.service;

import com.baizhi.dao.LbimgDAO;
import com.baizhi.entity.Lbimg;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class LbimgServiceImpl implements LbimgService {
    @Autowired
    private LbimgDAO lbimgDAO;

    @Override
    public void insertLb(Lbimg lbimg) {
        lbimg.setL_create_date(new Date());
        int i = lbimgDAO.insertSelective(lbimg);
        if (i == 0) throw new RuntimeException("添加失败");
    }

    @Override
    public void deleteLb(Lbimg lbimg) {
        lbimg.setL_status("未激活");
        int i = lbimgDAO.updateByPrimaryKeySelective(lbimg);
        if (i == 0) throw new RuntimeException("删除失败");
    }

    @Override
    public void updateLb(Lbimg lbimg) {
        if (lbimg.getL_cover().equals("")) {
            lbimg.setL_cover(null);
        }
        int i = lbimgDAO.updateByPrimaryKeySelective(lbimg);
        if (i == 0) throw new RuntimeException("修改失败");
    }

    @Override
    public List<Lbimg> showLbimg(Integer page, Integer row) {
        Example example = new Example(Lbimg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("l_status", "激活");
        RowBounds rowBounds = new RowBounds((page - 1) * row, row);
        List<Lbimg> lbimgs = lbimgDAO.selectByExampleAndRowBounds(example, rowBounds);
        return lbimgs;
    }

    @Override
    public Integer selectPages(Integer row) {
        Integer pages = 0;
        Example example = new Example(Lbimg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("l_status", "激活");
        int i = lbimgDAO.selectCountByExample(example);
        if (i % row != 0) {
            pages = i / row + 1;
        } else {
            pages = i / row;
        }
        return pages;
    }

    @Override
    public Integer selectTotal() {
        Example example = new Example(Lbimg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("l_status", "激活");
        int i = lbimgDAO.selectCountByExample(example);
        return i;
    }

    @Override
    public List<Lbimg> showAll() {
        List<Lbimg> lbimgs = lbimgDAO.selectAll();
        return lbimgs;
    }
}
