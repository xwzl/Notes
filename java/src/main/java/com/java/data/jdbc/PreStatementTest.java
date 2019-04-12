package com.java.data.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * @author xuweizhi
 * @date 2019/04/09 13:49
 */
public class PreStatementTest {

    Connection con = null;
    PreparedStatement ps = null;

    @Before
    public void getConnection() {
        con = ConnectionUtils.getConnections("192.168.26.20:3306/wtf", "root", "158262751");
    }

    @Test
    public void test1() throws SQLException {
        String sql = "select * from user";
        ps = con.prepareStatement(sql);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            ResultSetMetaData data = set.getMetaData();
            System.out.print(set.getString(1) + "\t");
            System.out.print(set.getString(2) + "\t");
            System.out.print(set.getString(3) + "\t");
            System.out.print(set.getString(4) + "\t");
            System.out.print(set.getString(6) + "\t");
            System.out.print(set.getString(7) + "\t");
            System.out.print(set.getString(8) + "\n");
        }
    }

    @Test
    public void test2() throws SQLException {
        String sql = "select * from user where address = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, "山东");
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            System.out.println("address:" + set.getString("address:") + " name" + set.getString("username"));
        }
    }


    @Test
    public void addUser() throws SQLException {
        String sql = "INSERT INTO user values(null,?,?,?,?,?,?,?)";
        ps = con.prepareStatement(sql);
        ps.setString(1, "1");
        ps.setString(2, "2");
        ps.setDate(3, new Date(System.currentTimeMillis()));
        ps.setString(4, "4");
        ps.setString(5, "5");
        ps.setInt(6, 5);
        ps.setString(7, "7");
        boolean result = ps.execute();
        System.out.println(result);
    }

    @Test
    public void delete() throws SQLException {
        String sql = "delete from user where u_id > ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, "10");
        ps.execute();
    }

    @After
    public void close() {
        ConnectionUtils.close(ps, con);
    }
}
