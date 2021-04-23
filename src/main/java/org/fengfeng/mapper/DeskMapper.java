package org.fengfeng.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;
import org.fengfeng.entry.Desk;

import java.util.Map;


public interface DeskMapper {
    @Insert("insert into desk(name,id,tp,feet) values(#{desk.name},#{desk.id},#{desk.tp},#{desk.feet})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insert(@Param("desk") Desk desk);
    @Select("select * from desk where id=#{id}")
    public Desk select(@Param("id") int id);

    public Map selectDesk1(@Param("id") int id);

    @Select("select * from desk WHERE id = #{id} ")
    public Map selectMap(@Param("id") int id);

}
