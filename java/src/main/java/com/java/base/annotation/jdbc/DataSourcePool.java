package com.java.base.annotation.jdbc;

import com.java.base.annotation.util.LogUtils;
import com.java.data.jdbc.ConnectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2019/04/09 13:31
 */
@Slf4j
public class DataSourcePool {

    private List<Connection> poolConnection = new ArrayList<>();

    private DateSource dateSource;

    private Connection getConnections(String host, String username, String password) {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + host + "?useUnicode=true&characterEncoding=" +
                    "UTF-8&autoReconnect=true&useSSL=false" +
                    "&autoReconnect=true&useSSL=false", username, password);
            LogUtils.printLog(log, "数据库初始化成功");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(PreparedStatement pre, Connection con) {
        try {
            if (pre != null) {
                pre.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection con = ConnectionUtils.getConnections("192.168.26.20:3306/wtf", "root", "158262751");
        ConnectionUtils.close(null, con);
    }

}
