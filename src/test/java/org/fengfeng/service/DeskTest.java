package org.fengfeng.service;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fengfeng.entry.Desk;
import org.fengfeng.mapper.DeskMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DeskTest {
// 返回的是 SessionFactory 所以是工厂模式的工厂， 是用于创建 sqlSession 的
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
    public void insert(){
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DeskMapper deskMapper = session.getMapper(DeskMapper.class);
            Desk desk = new Desk(0,"home","八仙桌",4);
            deskMapper.insert(desk);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void select() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DeskMapper deskMapper = session.getMapper(DeskMapper.class);
            int id=21;
            Desk select = deskMapper.select(id);
            System.out.println(select);
        }
    }
    @Test
    public void selectMap() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DeskMapper deskMapper = session.getMapper(DeskMapper.class);
            int id=21;
            Map map = deskMapper.selectMap(id);
            System.out.println(map);
        }
    }

    @Test
    public void selectMap1() throws IOException {


        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map o = session.selectOne("org.fengfeng.mapper.DeskMapper.selectDesk1",21);
            System.out.println(o);
        }
    }

}
