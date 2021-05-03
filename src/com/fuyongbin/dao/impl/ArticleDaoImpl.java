package com.fuyongbin.dao.impl;

import com.fuyongbin.dao.ArticleDao;
import com.fuyongbin.domain.Article;
import com.fuyongbin.domain.Category;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class ArticleDaoImpl extends HibernateDaoSupport implements ArticleDao {
    @Override
    public List<Article> getAllArticle() {
        System.out.println("articleDao");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        List<Article> list = (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public Integer getTotalCount(DetachedCriteria detachedCriteria) {
        /*设置条件只投影出有几条数据*/
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize) {
        /*清空；取消查询总数*/
        detachedCriteria.setProjection(null);
        List<Article> list = (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    @Override
    public void delete(Article article) {
        this.getHibernateTemplate().delete(article);
    }

    @Override
    public List<Category> getCategory(Integer parentid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        detachedCriteria.add(Restrictions.eq("parentid",parentid));
        List<Category> list = (List<Category>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public void save(Article article) {
        this.getHibernateTemplate().save(article);
    }

    @Override
    public Article getoneArticle(Integer article_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        detachedCriteria.add(Restrictions.eq("article_id",article_id));
        List<Article> list = (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void updata(Article article) {
        this.getHibernateTemplate().update(article);
    }
}
