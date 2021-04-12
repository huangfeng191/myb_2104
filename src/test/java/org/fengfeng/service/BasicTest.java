package org.fengfeng.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fengfeng.entry.Blog;
import org.fengfeng.mapper.BlogMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class BasicTest {
    public  SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
    @Test
    public void testForPath() throws IOException {
//         全限定类名方式

        SqlSessionFactory sqlSessionFactory =getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
//            全限定类名方式
            Blog blog = (Blog) session.selectOne("org.fengfeng.mapper.BlogMapper.selectBlog", 101);
            System.out.println(blog);
        }finally {
            session.close();
        }
    }
    @Test
    public void testForMapper() throws IOException {
        SqlSessionFactory sqlSessionFactory =getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
     try{
         BlogMapper mapper = session.getMapper(BlogMapper.class);
         Blog blog = mapper.selectBlog(101);
         System.out.println(blog);

     }finally {
         session.close();
     }

    }
}
