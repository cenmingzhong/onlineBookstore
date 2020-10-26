package com.cenmingzhong.test;

import com.cenmingzhong.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author cenmingzhong
 * @create 2020-09-12-下午 16:08
 */
public class JdbcUtilstest {

    @Test
    public void testJdbcUtils(){
        for (int i=0;i<100;i++){
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);

        }

    }

}
