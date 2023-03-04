package com.cx.fruit.transaction;

import com.cx.fruit.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/3-0:58
 * @Version 2022.2 1.8
 */
public class TransactionManager {



    public static void begin() throws SQLException {
        ConnectionUtil.getConnection().setAutoCommit(false);
    }

    public static void commit() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        connection.commit();
        ConnectionUtil.closeConnection();
    }

    public static void rollback() throws SQLException {
        ConnectionUtil.getConnection().rollback();
    }
}
