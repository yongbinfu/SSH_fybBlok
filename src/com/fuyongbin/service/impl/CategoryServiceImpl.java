package com.fuyongbin.service.impl;

import com.fuyongbin.dao.CategoryDao;
import com.fuyongbin.domain.Category;
import com.fuyongbin.service.CategoryService;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Setter
    public CategoryDao categoryDao;

    @Override
    public void save(Category category) {
        System.out.println(category.getCname());
        categoryDao.save(category);
    }

    @Override
    public List<Category> list() {
        System.out.println("service-----list");
        List<Category> list = categoryDao.getAllCategory();
        return list;
    }

    @Override
    public Category getOneCategory(Integer cid) {
        /*调用Dao层*/
        Category category=categoryDao.getOneCategory(cid);
        return category;
    }

    @Override
    public void updataOneData(Category category) {
        categoryDao.updataOneData(category);
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }


}
