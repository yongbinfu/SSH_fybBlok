package com.fuyongbin.dao;

import com.fuyongbin.domain.Category;

import java.util.List;

public interface CategoryDao {
    public void updataOneData(Category category);

    public void save(Category category);
    public List<Category> getAllCategory();

    public Category getOneCategory(Integer cid);

    public void delete(Category category);
}
