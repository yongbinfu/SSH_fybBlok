package com.fuyongbin.web;

import com.fuyongbin.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class PrivilegInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        System.out.println("PrivilegInterceptor-------------------------------------------------");
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("curUser");
        if (user==null){
            ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
            actionSupport.addActionError("您的登录已经超时，请重新登录！！！");
            return "login";
        }else {
            return actionInvocation.invoke();
        }
    }
}
