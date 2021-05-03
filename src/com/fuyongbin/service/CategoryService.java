package com.fuyongbin.service;

import com.fuyongbin.domain.Category;

import java.util.List;

public interface CategoryService {
    public void save(Category category);
    public List<Category> list();
    public Category getOneCategory(Integer cid);
    public void updataOneData(Category category);
    public void delete(Category category);
}
