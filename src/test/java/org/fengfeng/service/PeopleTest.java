package org.fengfeng.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fengfeng.entry.People;
import org.fengfeng.mapper.PeopleMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class PeopleTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
    @Test
    public void getPeople() throws IOException {
        Integer id =1;
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            PeopleMapper mapper = sqlSession.getMapper(PeopleMapper.class);
            People people = mapper.selectPeople(id);
            System.out.println(people);
        }finally {
            sqlSession.close();
        }
    }
}
