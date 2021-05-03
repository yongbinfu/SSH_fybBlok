package com.fuyongbin.web;

import com.fuyongbin.domain.Category;
import com.fuyongbin.service.CategoryService;
import com.mysql.cj.xdevapi.JsonArray;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.type.PostgresUUIDType;

import java.io.IOException;
import java.util.List;

public class CategoryAction extends ActionSupport implements ModelDriven<Category> {

    private Category category = new Category();

    @Override
    public Category getModel() {
        return category;
    }

    @Setter
    private CategoryService categoryService;

    public String add() {
        System.out.println("addCategory");
        System.out.println("Action:" + category);
        categoryService.save(category);
        return "listAction";
    }

    public String list() {
        System.out.println("action----list");
        List<Category> list = categoryService.list();

        /*把数据存放在值栈当中；因为push存放的是对象，而set存放的是集合*/
        ActionContext.getContext().getValueStack().set("categoryList", list);

        return "list";
    }

    public String updataUI() throws IOException {

        Integer cid = category.getCid();
        Category category2=categoryService.getOneCategory(category.getCid());
        System.out.println("action----updataUI" + category2);
        /*将获取的对象转成json数据格式*/
        JSONArray jsonArray = JSONArray.fromObject(category2, new JsonConfig());
        /*响应给浏览器*/
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }
    public String updata(){
        System.out.println("hfksdjaf");
        System.out.println(category);
        categoryService.updataOneData(category);
        return "listAction";
    }
    public String delete(){
        System.out.println("delete");
        System.out.println(category.getCid());
        categoryService.delete(category);
        return "listAction";
    }

}
