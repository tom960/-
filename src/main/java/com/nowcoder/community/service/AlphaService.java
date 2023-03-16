package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service//用于业务的Bean
//@Scope("prototype")
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    @PostConstruct//让容器去替你管理这个方法：在构造器后调用这个方法
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy//销毁对象之前调用ta
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    //写一个方法模拟Alpheservice调用alpheDao
    public String find(){
        return alphaDao.select();
    }
}
