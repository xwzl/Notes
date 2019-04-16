package com.java.frame.jdbc;

import com.java.frame.util.LogUtils;
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

    public DataSourcePool(DataSource dataSource) {
        initConnections(dataSource);
    }

    private void initConnections(DataSource dataSource) {
        try {
            Class.forName(dataSource.getClassName());
            for (int i = 0; i < 10; i++) {
                poolConnection.add(DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword()));
            }
            LogUtils.printLog(log, "The database connection pool was successfully initialized !");
        } catch (ClassNotFoundException | SQLException e) {
            LogUtils.printLog(log, "数据库连接失败");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection con = null;
        if (poolConnection.size() >= 1) {
            con = poolConnection.get(0);
            poolConnection.remove(0);
            return con;
        } else {
            return con;
        }
    }

    public void close(Connection connection) {
        poolConnection.add(connection);
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

    //public static void main(String[] args) throws SQLException {
    //    Connection con = ConnectionUtils.getConnections("192.168.26.20:3306/wtf", "root", "158262751");
    //    ConnectionUtils.close(null, con);
    //}

}
