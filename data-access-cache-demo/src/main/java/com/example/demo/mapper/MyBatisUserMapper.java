package com.example.demo.mapper;

import com.example.demo.mybatis.entity.MyBatisUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MyBatisUserMapper {
    
    @Select("SELECT * FROM users WHERE id = #{id}")
    MyBatisUser findById(Long id);
    
    @Insert("INSERT INTO users(username, email, age) VALUES(#{username}, #{email}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(MyBatisUser user);
    
    @Update("UPDATE users SET username = #{username}, email = #{email}, age = #{age} WHERE id = #{id}")
    int update(MyBatisUser user);
    
    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteById(Long id);
    
    @Select("SELECT * FROM users WHERE username LIKE CONCAT('%', #{username}, '%')")
    java.util.List<MyBatisUser> findByUsernameLike(String username);
}