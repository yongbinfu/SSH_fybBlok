package com.fuyongbin.service.impl;

import com.fuyongbin.dao.ArticleDao;
import com.fuyongbin.domain.Article;
import com.fuyongbin.domain.Category;
import com.fuyongbin.domain.PageBean;
import com.fuyongbin.service.ArticleService;
import lombok.Setter;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.jsp.tagext.PageData;
import java.util.List;

@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Setter
    private ArticleDao articleDao;

    @Override
    public List<Article> list() {
        System.out.println("ArticleService");
        List<Article> allArticle = articleDao.getAllArticle();
        return allArticle;
    }

    @Override
    public PageBean getPageList(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<Article> pageBean = new PageBean<>();
        /*设置当前页数*/
        pageBean.setCurrentPage(currPage);
        /*设置当前页有几条数据*/
        pageBean.setPageSize(pageSize);
        /*获取总记录数*/
        /*从数据库当中查询总记录数*/
        Integer totalCount=articleDao.getTotalCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        /*获取总页数*/
        pageBean.setTotalPage(pageBean.getTotalPage());
        /*设置总记录数*/
        /*设置数据当前页数据*/
        List<Article> list=articleDao.getPageData(detachedCriteria,pageBean.getIndex(),pageBean.getPageSize());
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void delete(Article article) {
        articleDao.delete(article);
    }

    @Override
    public List<Category> getCategory(Integer parentid) {
        List<Category> list=articleDao.getCategory(parentid);
        return list;
    }

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public Article getoneArticle(Integer article_id) {
        Article res=articleDao.getoneArticle(article_id);
        return res;
    }

    @Override
    public void updata(Article article) {
        articleDao.updata(article);
    }


}
