package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaDaoHibernate")//定义为数据库类的Bean
//spring会自动扫描这个Bean，把它装入Spring容器
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select(){
        return "Hibernate";
    }
}
