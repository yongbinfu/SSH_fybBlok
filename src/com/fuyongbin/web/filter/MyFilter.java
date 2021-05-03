package com.fuyongbin.web.filter;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter extends StrutsPrepareAndExecuteFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        /*获取当前的请求*/
        HttpServletRequest request= (HttpServletRequest) req;
        /*获取请求地址进行判断是否包含富文本编辑器的jsp*/
        String requestURI = request.getRequestURI();
        if (requestURI.contains("js/umedit/jsp/controller.jsp")){
            /*放行*/
            chain.doFilter(req,res);
        }else {
            /*调用父类的过滤器*/
            super.doFilter(req, res, chain);
        }

    }
}
