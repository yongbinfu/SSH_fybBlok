package com.fuyongbin.web;

import com.fuyongbin.domain.Article;
import com.fuyongbin.domain.PageBean;
import com.fuyongbin.service.ArticleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import netscape.javascript.JSObject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;

public class WebAction extends ActionSupport {

    @Setter
    private ArticleService articleService;

    @Setter
    private Integer currPage = 1;

    public void getPageList() throws IOException {
        System.out.println("hdhjfjdshjkfskha");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        /*调用业务层*/
        PageBean pageBean = articleService.getPageList(detachedCriteria, currPage, 5);
        //已json返回给web
        JsonConfig jsonConfig = new JsonConfig();
        //hibernate 延时加载
        jsonConfig.setExcludes(new String[]{"handler", "hibernateLazyInitializer"});
        JSONObject jsonObject = JSONObject.fromObject(pageBean,jsonConfig);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonObject.toString());

    }
}
