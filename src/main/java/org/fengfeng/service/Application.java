package org.fengfeng.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fengfeng.entry.Blog;


import java.io.IOException;
import java.io.InputStream;

public class Application {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Blog blog = (Blog) session.selectOne("org.fengfeng.mapper.BlogMapper.selectBlog", 101);
            System.out.println(blog);
        }

    }
}
