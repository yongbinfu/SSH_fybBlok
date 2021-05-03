package com.fuyongbin.dao.impl;

import com.fuyongbin.dao.CategoryDao;
import com.fuyongbin.domain.Category;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {
    @Override
    public void updataOneData(Category category) {
        this.getHibernateTemplate().update(category);
    }

    @Override
    public void save(Category category) {
        System.out.println("dao");
        this.getHibernateTemplate().save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        System.out.println("dao-----list");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        List<Category> list = (List<Category>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public Category getOneCategory(Integer cid){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        detachedCriteria.add(Restrictions.eq("cid",cid));

            List<Category> list = (List<Category>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
            return list.get(0);

    }

    @Override
    public void delete(Category category) {
        this.getHibernateTemplate().delete(category);
    }
}
