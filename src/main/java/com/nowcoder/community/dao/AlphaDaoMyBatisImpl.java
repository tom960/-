package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary//导致这个Bean会优先被加载
// （因为测试类中是指的是AlphaDao接口下的Bean）这时候这个接口下面有两个Bean,所以要定义一下
public class AlphaDaoMyBatisImpl implements AlphaDao{

    @Override
    public String select() {
        return "MyBatis";
    }
}
