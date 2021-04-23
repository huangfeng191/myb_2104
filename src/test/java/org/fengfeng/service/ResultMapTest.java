package org.fengfeng.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fengfeng.entry.Author;
import org.fengfeng.entry.BlogAndAuthor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ResultMapTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory bindSqlSessionFactory() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        return sqlSessionFactory;
    }
    @Before
    public void init(){
        System.out.println("I'm already initialized ;");
        try {
            sqlSessionFactory = bindSqlSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void selectDeskByResultMap() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map o = session.selectOne("org.fengfeng.mapper.DeskMapper.selectDeskByResultMap",21);
            System.out.println(o);
        }
    }

    @Test
    public void selectBlogAndAuthor() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
//            Author o = session.selectOne("org.fengfeng.mapper.BlogAndAuthorMapper.selectAuthor",8);
//            System.out.println(o);

            BlogAndAuthor blogAndAuthor = session.selectOne("org.fengfeng.mapper.BlogAndAuthorMapper.selectBlog",101);
            System.out.println(blogAndAuthor);

        }
    }

    @Test
    public void selectBlogAndAuthors_select() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<BlogAndAuthor> objects = session.selectList("org.fengfeng.mapper.BlogAndAuthorMapper.selectBlogs");
            System.out.println("---------------------------------");
            System.out.println(objects);

        }
    }

    @Test
    public void selectBlogAndAuthor_nest() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<BlogAndAuthor> objects = session.selectList("org.fengfeng.mapper.BlogAndAuthorMapper.selectBlogByNest");
            System.out.println("---------------------------------");
            System.out.println(objects);

        }
    }



}
