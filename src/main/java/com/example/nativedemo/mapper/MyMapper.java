package com.example.nativedemo.mapper;

import com.example.nativedemo.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyMapper {

//    @Insert("""
//          INSERT INTO messages (message)
//            VALUES (#{message})
//        """)
    void insert(Message message);

//    @Select("""
//          SELECT
//            id
//            ,message
//          FROM
//            messages
//          WHERE
//            id = #{id}
//        """)
    Message select(Integer id);
}
