package com.fuyongbin.dao.impl;

import com.fuyongbin.dao.UserDao;
import com.fuyongbin.domain.User;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {


    @Override
    public User getUser(String username, String password) {
        /*告诉它要从数据库中的哪一个表去查询数据
        * 设置去哪一个表中去查*/
        System.out.println("Dao:"+username + password);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        /*设置条件*/
        detachedCriteria.add(Restrictions.eq("username",username));
        detachedCriteria.add(Restrictions.eq("password",password));
        List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
