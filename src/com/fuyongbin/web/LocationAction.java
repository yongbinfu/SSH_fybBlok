package com.fuyongbin.web;

import com.opensymphony.xwork2.ActionSupport;

public class LocationAction extends ActionSupport {

    public String left(){
        return "left";
    }
    public String top(){
        return "top";
    }
    public String account(){
        return "account";
    }
    public String addArticle(){
        return "addArticle";
    }

}
