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

public class Cache {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory =getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try{
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = mapper.selectBlog(101);
            Blog blog1 = mapper.selectBlog(101);
            Blog blog2 = mapper.selectBlog(101);
            // 只查询一次 默认一级缓存开启
            System.out.println(blog);
            System.out.println(blog1);
            System.out.println(blog2);

        }finally {
            session.close();
        }
    }

    @Test
    public void test2Cache() throws IOException {
        SqlSessionFactory sqlSessionFactory =getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        SqlSession session1 = sqlSessionFactory.openSession();
        try{
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            BlogMapper mapper1 = session1.getMapper(BlogMapper.class);
            Blog blog = mapper.selectBlog(101);
            System.out.println(blog);
            session.close();
//           默认情况下，只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存。 要启用全局的二级缓存，只需要在你的 SQL 映射文件中添加一行：
//            2 级缓存开启 需要序列化 是不是为了其他的缓存实现 , 缓存命中率 指的是2级缓存？
            Blog blog1 = mapper1.selectBlog(101);
            Blog blog2 = mapper1.selectBlog(101);
            System.out.println(blog);

        }finally {

            session1.close();
        }
    }

}
