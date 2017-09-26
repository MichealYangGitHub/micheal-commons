package com.michealyang.auth.dao;

import com.michealyang.auth.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

/**
 * @author michealyang
 * @version 1.0
 * @created 17/7/21
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public interface UserDao {
    final String TABLE_NAME = "user";

    @Select("select count(name) from " + TABLE_NAME + " where name=#{userName}")
    public int getUserNameNum(String userName);

    @Select("select salt from " + TABLE_NAME + " where name=#{userName}")
    public String getSaltByUserName(String userName);

    @Select("select * from " + TABLE_NAME + " where name=#{userName}")
    public User getUserByUserName(String userName);

    @InsertProvider(type = SqlProvider.class, method = "insert")
    public int insert(User user);

    class SqlProvider {
        public String insert(User user) {
            BEGIN();
            INSERT_INTO(TABLE_NAME);
            if(StringUtils.isNotBlank(user.getName())){
                VALUES("name", "#{name}");
            }
            if(StringUtils.isNotBlank(user.getPasswd())){
                VALUES("passwd", "#{passwd}");
            }
            if(StringUtils.isNotBlank(user.getSalt())){
                VALUES("salt", "#{salt}");
            }
            if(StringUtils.isNotBlank(user.getTelephone())){
                VALUES("telephone", "#{telephone}");
            }
            if(StringUtils.isNotBlank(user.getEmail())){
                VALUES("email", "#{email}");
            }
            VALUES("ctime", "unix_timestamp()");

            return SQL();
        }
    }
}
