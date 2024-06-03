package com.yuanlrc.campus_market.controller.home;
import static org.junit.Assert.assertTrue;

import com.yuanlrc.campus_market.dao.common.StudentDao;
import com.yuanlrc.campus_market.entity.common.Student;
import com.yuanlrc.campus_market.service.common.StudentService;
import com.yuanlrc.campus_market.service.common.DBconnection;
import com.yuanlrc.campus_market.bean.Result;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class IndexControllerTest extends Student
{
    @Autowired
    IndexController indexController;

    @MockBean
    StudentDao studentDao;

    @BeforeEach
    void setUp() {
        Mockito.when(studentDao.findBySn("10000")).thenReturn(new Student());
    }
    @Test
    void loginfail() throws SQLException {
        DBconnection db=new DBconnection();
        String nsql = "SELECT * FROM ylrc_student WHERE sn = 10000 AND password = 1999";
        ResultSet rss =db.executeQuery(nsql);
        while (rss.next()) {


            System.out.println(rss.getString("nickname"));
            Assert.assertNull(rss.getString("nickname"));
        }
        rss.close();

    }
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school?allowPublicKeyRetrieval=true&serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";

    @Test
    public  void loginsuccess() throws SQLException {
        DBconnection db=new DBconnection();
        String nsql = "SELECT * FROM ylrc_student WHERE sn = 10000 AND password = 123456";
        ResultSet rss =db.executeQuery(nsql);
        while (rss.next()) {


            System.out.println(rss.getString("nickname"));
            Assert.assertEquals(rss.getString("nickname"),"张三");
        }
        rss.close();
    }

    @Test
    public void logins() {
        String username = "10000";
        String password = "123456";
        DBconnection db=new DBconnection();
        try {
            db.getCon();
            System.out.println("数据库连接成功");
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ylrc_student WHERE sn = ? AND password = ?")) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            System.out.print(rs.wasNull());
            //Assert.assertNotNull(rs.getString("nicename"));
            //System.out.println("Nickname: " + rs.getString("nicename"));
            if (rs.next()) { // 检查是否有结果
                System.out.println("Found user.");
                System.out.println("Nickname: " + rs.getString("nicename"));

            } else {
                System.out.println("User not found.");
            }


        } catch (SQLException e) {
            System.out.print("Failed to connect to the database");
        }

    }
}
