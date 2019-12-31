package com.baizhi.service;

import com.baizhi.entity.Lbimg;

import java.util.List;

public interface LbimgService {
    public void insertLb(Lbimg lbimg);

    public void deleteLb(Lbimg lbimg);

    public void updateLb(Lbimg lbimg);

    public List<Lbimg> showLbimg(Integer page, Integer row);

    public Integer selectPages(Integer row);

    public Integer selectTotal();

    public List<Lbimg> showAll();
}
