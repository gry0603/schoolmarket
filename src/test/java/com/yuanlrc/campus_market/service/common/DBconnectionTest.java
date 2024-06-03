package com.yuanlrc.campus_market.service.common;

import com.yuanlrc.campus_market.dao.common.StudentDao;
import com.yuanlrc.campus_market.entity.common.Student;
import com.yuanlrc.campus_market.service.common.StudentService;
import com.yuanlrc.campus_market.service.common.DBconnection;
import com.yuanlrc.campus_market.bean.Result;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

@SpringBootTest
public class DBconnectionTest {

    @Test
    public  void executeQuery() throws SQLException {
        DBconnection db=new DBconnection();
        String nsql = "SELECT * FROM ylrc_student";
        ResultSet rss =db.executeQuery(nsql);
        while (rss.next()) {

            System.out.println(rss.getString("nickname"));
        }
        rss.close();
    }
    @Test
    public void consuccess() {
        DBconnection db=new DBconnection();

        try {
            Connection cn=db.getCon();
            System.out.println("数据库连接成功");
            System.out.println(cn.getMetaData());
            Assert.assertNotNull(cn);

        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }

    }

}