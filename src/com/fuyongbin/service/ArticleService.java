package com.fuyongbin.service;

import com.fuyongbin.domain.Article;
import com.fuyongbin.domain.Category;
import com.fuyongbin.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleService {
    public List<Article> list();

    public PageBean getPageList(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    public void delete(Article article);

    public List<Category> getCategory(Integer parentid);

    public void save(Article article);

    public Article getoneArticle(Integer article_id);

    public void updata(Article article);
}
