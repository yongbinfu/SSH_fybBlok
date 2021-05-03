package com.fuyongbin.web;

import com.fuyongbin.domain.Article;
import com.fuyongbin.domain.Category;
import com.fuyongbin.domain.PageBean;
import com.fuyongbin.service.ArticleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.sun.org.apache.xpath.internal.SourceTree;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ArticleAction extends ActionSupport implements ModelDriven<Article> {

    private Article article = new Article();

    @Override
    public Article getModel() {
        return article;
    }

    @Setter
    public ArticleService articleService;

    public String list() {
        System.out.println("articlelist");
        List<Article> articleList = articleService.list();
        System.out.println(articleList);
        /*存入值栈中去*/
        ActionContext.getContext().getValueStack().set("articleList", articleList);
        return "list";
    }

    @Setter
    private Integer currPage = 1;
    @Setter
    private String content;

    public String pageList() {
        System.out.println("currentPage:" + currPage);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);

        if (content != null) {
            detachedCriteria.add(Restrictions.like("article_title", "%" + content + "%"));
        }

        /*调用业务层*/
        PageBean pageBean = articleService.getPageList(detachedCriteria, currPage, 5);
        System.out.println(pageBean);
        /*数据存放到值栈当中*/
        ActionContext.getContext().getValueStack().push(pageBean);
        return "list";
    }

    public String delete() {
        System.out.println("delete");
        articleService.delete(article);
        return "listres";
    }

    @Setter
    private Integer parentid;

    public String getCategory() throws IOException {
        System.out.println(parentid);
        /*根据parentid查询分类数据*/
        List<Category> list = articleService.getCategory(parentid);
        JSONArray jsonArray = JSONArray.fromObject(list, new JsonConfig());
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }

    /**
     * 文件上传提供的三个属性:
     */
    @Setter
    private String uploadFileName; // 文件名称
    @Setter
    private File upload; // 上传临时文件
    @Setter
    private String uploadContentType; // 文件类型

    public String add() throws IOException {
        System.out.println("aaaa");
        System.out.println(uploadContentType);
        System.out.println(upload);
        System.out.println(uploadFileName);
        /*获取上传的路径*/
        if (upload != null) {
            /*随机生成文件名称*/
            int lastIndexOf = uploadFileName.lastIndexOf(".");
            String etx = uploadFileName.substring(lastIndexOf);
            System.out.println(etx);
            /*随机生成文件名；拼接扩展名*/
            String uuidFileName = UUID.randomUUID().toString().replace("-", "") + etx;
            System.out.println(uuidFileName);
            String realPath = ServletActionContext.getServletContext().getRealPath("/upload");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File diskFile = new File(realPath + "/" + uuidFileName);
            FileUtils.copyFile(upload, diskFile);
            article.setArticle_pic(uuidFileName);
        }
        article.setArticle_time(System.currentTimeMillis());
        System.out.println(article);
        articleService.save(article);
        return "listres";
    }

    public String edit() {
        System.out.println(article.getArticle_id());
        Article res = articleService.getoneArticle(article.getArticle_id());
        System.out.println("askdjhksahdksahdhksa" + res);
        ActionContext.getContext().getValueStack().push(res);
        return "edit";
    }

    public String updata() throws IOException {
        article.setArticle_time(System.currentTimeMillis());
        if (upload != null) {
            /*文件上传*/
            /*设置上传路径*/
            String realPath = ServletActionContext.getServletContext().getRealPath("/upload");
            /*获取原本的文件名称*/
            String article_pic = article.getArticle_pic();
            /*有改变的话删除原本的图片*/
            if (article_pic!=null || !"".equals(article_pic)){
                File file = new File(realPath + article_pic);
                file.delete();
                System.out.println(true);
            }
            int idx = uploadFileName.lastIndexOf(".");
            String substring = uploadFileName.substring(idx);
            String uuidFileName=UUID.randomUUID().toString().replace("-","")+substring;
            File file = new File(realPath);
            if (!file.exists()){
                file.mkdirs();
            }
            File diskFile = new File(realPath + "/" + uuidFileName);
            FileUtils.copyFile(upload, diskFile);
            article.setArticle_pic(uuidFileName);
            System.out.println(article);
        }
        articleService.updata(article);

        return "listres";
    }

}
