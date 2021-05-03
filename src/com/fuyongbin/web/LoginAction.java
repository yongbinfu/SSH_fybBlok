package com.fuyongbin.web;

import com.fuyongbin.domain.User;
import com.fuyongbin.service.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;

public class LoginAction extends ActionSupport implements ModelDriven<User> {

    public User user = new User();

    @Override
    public User getModel() {

        return user;
    }

    public LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
/*登录*/
    public String login() {
        System.out.println("来了LoginAction");
        System.out.println(user);
        User resUser = loginService.login(user);
        if (resUser==null){
            this.addActionError("用户密码错误");
            return LOGIN;
        }else {
           ActionContext.getContext().getSession().put("curUser", resUser);
            return SUCCESS;
        }

    }
/*退出*/
    public String loginout(){
        ActionContext.getContext().getSession().remove("curUser");
        System.out.println("exit");
        return "login_out";
    }

}
