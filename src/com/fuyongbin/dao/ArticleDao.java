package com.fuyongbin.dao;

import com.fuyongbin.domain.Article;
import com.fuyongbin.domain.Category;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleDao {
    public List<Article> getAllArticle();


    public Integer getTotalCount(DetachedCriteria detachedCriteria);


    public List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize);


    public void delete(Article article);

    public List<Category> getCategory(Integer parentid);

    public void save(Article article);

    public Article getoneArticle(Integer article_id);

    public void updata(Article article);
}
